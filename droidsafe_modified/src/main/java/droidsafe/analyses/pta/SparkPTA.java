/*
 * Copyright (C) 2015,  Massachusetts Institute of Technology
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 * Please email droidsafe@lists.csail.mit.edu if you need additional
 * information or have any questions.
 */

package droidsafe.analyses.pta;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.output.NullOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.HashBiMap;

import soot.AnySubType;
import soot.ArrayType;
import soot.Body;
import soot.Context;
import soot.G;
import soot.Hierarchy;
import soot.Local;
import soot.MethodContext;
import soot.MethodOrMethodContext;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.Type;
import soot.Unit;
import soot.Value;

import java.util.List;

import soot.jimple.ArrayRef;
import soot.jimple.AssignStmt;
import soot.jimple.DynamicInvokeExpr;
import soot.jimple.InstanceFieldRef;
import soot.jimple.InstanceInvokeExpr;
import soot.jimple.InterfaceInvokeExpr;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.jimple.NewExpr;
import soot.jimple.NewMultiArrayExpr;
import soot.jimple.NullConstant;
import soot.jimple.SpecialInvokeExpr;
import soot.jimple.StaticFieldRef;
import soot.jimple.StaticInvokeExpr;
import soot.jimple.Stmt;
import soot.jimple.VirtualInvokeExpr;
import soot.jimple.spark.SparkEvaluator;
import soot.jimple.spark.SparkTransformer;
import soot.jimple.spark.geom.dataMgr.Obj_full_extractor;
import soot.jimple.spark.geom.geomPA.GeomPointsTo;
import soot.jimple.spark.geom.geomPA.GeomQueries;
import soot.jimple.spark.geom.geomPA.IVarAbstraction;
import soot.jimple.spark.internal.TypeManager;
import soot.jimple.spark.pag.AllocNode;
import soot.jimple.spark.pag.InsensitiveAllocNode;
import soot.jimple.spark.pag.MethodPAG;
import soot.jimple.spark.pag.Node;
import soot.jimple.spark.pag.ObjectSensitiveAllocNode;
import soot.jimple.spark.pag.PAG;
import soot.jimple.spark.sets.HashPointsToSet;
import soot.jimple.spark.sets.P2SetVisitor;
import soot.jimple.spark.sets.PointsToSetInternal;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Edge;
import soot.jimple.toolkits.callgraph.EdgePredicate;
import soot.jimple.toolkits.callgraph.Filter;
import soot.jimple.toolkits.callgraph.ReachableMethods;
import soot.jimple.toolkits.callgraph.VirtualCalls;
import soot.jimple.toolkits.pta.IAllocNode;
import soot.toolkits.scalar.Pair;
import soot.util.queue.QueueReader;
import droidsafe.analyses.CallGraphDumper;
import droidsafe.analyses.allocationgraph.AllocationGraph;
import droidsafe.android.app.Harness;
import droidsafe.android.app.Project;
import droidsafe.android.system.API;
import droidsafe.main.Config;
import droidsafe.transforms.objsensclone.ObjectSensitivityCloner;
import droidsafe.utils.CannotFindMethodException;
import droidsafe.utils.MutableInt;
import droidsafe.utils.SootUtils;
import droidsafe.utils.SourceLocationTag;

/**
 * A PTA bridge for the SPARK points to analysis.  Right now the analysis is context insensitive.
 * 
 * @author mgordon
 *
 */
public class SparkPTA extends PTABridge {
	/** Logger field */
	private static final Logger logger = LoggerFactory.getLogger(SparkPTA.class);
	/** the value of k for obj sensitivity depth that we were run with */
	private int k;
	/** bimap of new expressions to their alloc node representation */
	private HashBiMap<Object, InsensitiveAllocNode> newToAllocNodeMap;
	/** underlying pta */
	private PAG ptsProvider;

	private TypeManager typeManager;

	private Set<AllocNode> allAllocNodes;
	/** how many times have we been run? */
	private static int runCount = 1;

	/** if option limitcontextforcomplex then limit the context for this percent of the 
	 * classes.  Higher number will limit more context, be more scaleable but less precise.
	 */
	private static final double PERCENTAGE_TO_LIMIT_COMPLEXITY = 0.5;

	/** comma separated list of classes in which no matter what the length of k
	 * for object sensitivity, we want to limit the depth of the object sensitivity 
	 * to one.  Also add subclasses of each 
	 * 
	 * Strings will be added if the precisestrings options is given
	 */
	private static String[]  NO_CONTEXT = { 
			"java.lang.Throwable",
			"java.math.BigInt",
			"java.math.BigInteger",
			"android.graphics.Rect",
			"android.view.MotionEvent",
			"android.view.KeyEvent",
			"android.graphics.Point"

	};


	/** package prefix of important allocators from java */
	private static String[] IMPORTANT_ALLOCATORS_FROM_JAVA = {
			"java.io",
			"java.lang",
			"java.math",
			"java.net",
			"java.nio",
			"java.util",
			"java.text"
	};

	public SparkPTA(Map<String,String> opts) {
		super(opts);
	}

	@Override
	protected void releaseInternal() {
	}

	
	public PAG getPtsProvider() {
		return ptsProvider;
	}

	@Override
	protected void runInternal() {   
		//don't print crap to screen!
		G.v().out = new PrintStream(NullOutputStream.NULL_OUTPUT_STREAM);
		Scene.v().loadDynamicClasses();

		setSparkPointsToAnalysis();

		//other passes can print crap now
		G.v().out = System.out;

		ptsProvider = (PAG)Scene.v().getPointsToAnalysis();

		typeManager = ptsProvider.getTypeManager();

		//cache the call graph
		callGraph = Scene.v().getCallGraph();

		createNewToAllocMap();

		long totalMCs = 0;
		for (SootMethod method : getReachableMethods()) {
			Set<MethodOrMethodContext> mcs = getMethodContexts(method);
			totalMCs += mcs.size();
			/*    if (mcs.size() > 30)
                System.out.println(method + " " + mcs.size());
            if ("<java.lang.Math: int min(int,int)>".equals(method.getSignature())) {
                for (MethodOrMethodContext mc : mcs) {
                    System.out.println("\t" + mc);
                }
            }
			 */
		}

		System.out.println("Total reachable method x contexts: " + totalMCs);

		//dumpReachablesAndAllocNodes();
		//dumpCallGraphReachablesCSV();
		//dumpOutdegreesCSV();

		if (Config.v().dumpPta){
			dumpPTA(Project.v().getOutputDir() + File.separator +"pta.txt");
		}

		if (Config.v().dumpCallGraph) {
			//dumpCallGraph(Project.v().getOutputDir() + File.separator + "callgraph.dot");
			CallGraphDumper.runGEXF(Project.v().getOutputDir() + File.separator + "callgraph.gexf");
			//String fileName = String.format("callgraph%d.txt", runCount++);
			//dumpTextGraph(Project.v().getOutputDir() + File.separator + fileName);
		}

		//System.out.println(SparkEvaluator.v().toString());      
	}

	private void dumpReachablesAndAllocNodes() {
		try {
			FileWriter fw = new FileWriter(Project.v().getOutputDir() + File.separator + "spark-dump.log");

			fw.write("# Reachable Method Contexts:\n\n");

			for (MethodOrMethodContext momc : getReachableMethodContexts()) {
				fw.write(momc + "\n\n");
			}

			fw.write("\n\n# AllocNodes: \n\n");
			Iterator<AllocNode> nodes = ptsProvider.getAllocNodeNumberer().iterator(); 
			while (nodes.hasNext()) {
				AllocNode node = nodes.next();
				fw.write(node + "\n\n");                
			}

			fw.close();

		} catch (IOException e) {

		}

	}

	private void dumpOutdegreesCSV() {
		try {
			FileWriter fw = new FileWriter(Project.v().getOutputDir() + File.separator + "reachables-outdegree.csv");

			fw.write("Method,Outdegree");

			for (MethodOrMethodContext momc : getReachableMethodContexts()) {
				int outdegree = 0;
				Iterator<Edge> edges = callGraph.edgesOutOf(momc);
				while (edges.hasNext()) {
					edges.next();
					outdegree++;
				}

				fw.write(momc + "|" + outdegree + "\n");
			}

			fw.close();

		} catch (IOException e) {

		}
	}

	EdgePredicate noStaticInits = new EdgePredicate() {

		@Override
		public boolean want(Edge e) {
			return !("<clinit>".equals(e.tgt().getName()));
		}

	};

	/**
	 * Expensive!
	 */
	private void dumpCallGraphReachablesCSV() {
		try {
			FileWriter fw = new FileWriter(Project.v().getOutputDir() + File.separator + "reachables-count.csv");

			fw.write("Method,Reachables");


			for (MethodOrMethodContext momc : getReachableMethodContexts()) {
				if ("<clinit>".equals(momc.method().getName()))
					continue;

				Set<MethodOrMethodContext> c = new HashSet<MethodOrMethodContext>();            
				c.add(momc);
				Filter filter = new Filter(noStaticInits);
				//filter on static initializers, hopefully they won't show in the stats, 
				//or any calls that they make...
				ReachableMethods rm = new ReachableMethods(callGraph, c.iterator(), filter);
				rm.update();

				QueueReader<MethodOrMethodContext> edges = rm.listener();
				int reachables = 0;
				while (edges.hasNext()) {
					MethodOrMethodContext reachable = edges.next();
					if ("<clinit>".equals(reachable.method().getName()))
						continue;
					reachables++;
				}

				fw.write(momc + "|" + reachables + "\n");
			}

			fw.close();

		} catch (IOException e) {

		}
	}

	public CallGraph getCallGraph() {
		return callGraph;
	}


	public Set<MethodOrMethodContext> getMethodContexts(SootMethod method) {
		return SparkEvaluator.v().getContexts(method);
	}

	public Set<IAllocNode> getAllocNodeIns(Object newExpr) {
		InsensitiveAllocNode insens = null;
		if (newExpr instanceof NewMultiArrayExpr) {
			NewMultiArrayExpr newArr = (NewMultiArrayExpr)newExpr;
			ArrayType type = (ArrayType)newArr.getType();
			Integer i = type.numDimensions;
			Pair pair = new Pair(newArr, i);
			insens = newToAllocNodeMap.get(pair);
		} else {
			if (!newToAllocNodeMap.containsKey(newExpr)) {
				System.out.println("Not in new -> alloc map: " + newExpr);
				return null;
			}
			insens = newToAllocNodeMap.get(newExpr);
		}

		Set<IAllocNode> nodes = new LinkedHashSet<IAllocNode>();

		nodes.add(insens);
		nodes.addAll(insens.getContextNodeMap().values());

		return nodes;
	}

	/**
	 * Given a new expression (Jimple NewExpr or String) return the corresponding AllocNode.
	 */
	public IAllocNode getAllocNode(Object newExpr, Context context) {
		InsensitiveAllocNode insens = null;
		if (newExpr instanceof NewMultiArrayExpr) {
			NewMultiArrayExpr newArr = (NewMultiArrayExpr)newExpr;
			ArrayType type = (ArrayType)newArr.getType();
			Integer i = type.numDimensions;
			Pair pair = new Pair(newArr, i);
			insens = newToAllocNodeMap.get(pair);
		} else {
			if (!newToAllocNodeMap.containsKey(newExpr)) {
				System.out.println("Not in new -> alloc map: " + newExpr);
				return null;
			}
			insens = newToAllocNodeMap.get(newExpr);
		}

		//handle no context case
		if (k == 0)
			return insens;

		return insens.context(context);
	}

	/**
	 * Return a set of all allocnodes in the program.
	 */
	public Set<? extends IAllocNode> getAllAllocNodes() {
		return allAllocNodes;
	}


	@Override
	public boolean isLegalCast(Type objType, Type refType) {
		return ptsProvider.getTypeManager().castNeverFails(objType, refType);
	}

	@Override
	public Set<MethodOrMethodContext> getReachableMethodContexts() {
		return SparkEvaluator.v().getReachableMethodContexts();
	}

	@Override
	public Set<SootMethod> getReachableMethods() {
		return SparkEvaluator.v().getReachableMethods();
	}

	@Override
	public boolean isReachableMethod(SootMethod method) {
		return getReachableMethods().contains(method);
	}

	@Override
	public boolean isPointer(Value val) {
		return (ptsProvider.findGlobalVarNode(val) != null || ptsProvider.findLocalVarNode(val) != null);
	}

	@Override
	public Set<Type> getTypesIns(Value val) {
		Set<Type> types = new LinkedHashSet<Type>();

		for (IAllocNode node : getPTSetIns(val)) {
			types.add(node.getType());
		}

		return types;
	}

	@Override
	public Set<Type> getTypes(Value val, Context context) {
		Set<Type> types = new LinkedHashSet<Type>();

		for (IAllocNode node : getPTSet(val, context)) {
			types.add(node.getType());
		}

		return types;
	}

	@Override
	public Set<? extends IAllocNode> getPTSetIns(Value val) {
		final Set<AllocNode> allocNodes = new LinkedHashSet<AllocNode>();
		PointsToSetInternal pts = null;
		final Type filteringType = val.getType();

		try {
			if (val instanceof InstanceFieldRef) {
				final InstanceFieldRef ifr = (InstanceFieldRef)val;
				pts = (PointsToSetInternal)ptsProvider.reachingObjects((Local)ifr.getBase(), ifr.getField());
			} else if (val instanceof ArrayRef) {
				ArrayRef arrayRef = (ArrayRef)val;
				pts = (PointsToSetInternal)ptsProvider.reachingObjectsOfArrayElement
						(ptsProvider.reachingObjects((Local)arrayRef.getBase()));
			} else if (val instanceof Local){            
				pts = (PointsToSetInternal)ptsProvider.reachingObjects((Local)val);
			} else if (val instanceof StaticFieldRef) {
				SootField field = ((StaticFieldRef)val).getField();
				pts = (PointsToSetInternal)ptsProvider.reachingObjects(field);
			} else if (val instanceof NullConstant) {
				return (Set<? extends IAllocNode>) allocNodes;
			} else {
				logger.error("Unknown reference type for insenstive search: {} {}", val, val.getClass());
				//droidsafe.main.Main.exit(1);
			}

			//visit internal points to set and grab all allocnodes        
			pts.forall(new P2SetVisitor() {
				public void visit(Node n) {
					if (typeManager.castNeverFails(n.getType(), filteringType))
						allocNodes.add((AllocNode)n);
				}
			});

		} catch (Exception e) {
			logger.info("Some sort of error getting context insensitive points to set for {}", val, e);
			//e.printStackTrace();
		}

		return allocNodes;
	}


	public Set<? extends IAllocNode> getPTSetOfArrayElement(IAllocNode allocNode) {
		final Set<AllocNode> ptSet = new LinkedHashSet<AllocNode>();
		if (!(allocNode.getType() instanceof ArrayType)) {
			logger.error("Calling getPTSetOfArrayElement on non array type: {} with {}", allocNode, allocNode.getType());
		}

		final Type filteringType = ((ArrayType)allocNode.getType()).getElementType();

		HashPointsToSet pointsToSet = new HashPointsToSet(allocNode.getType(), ptsProvider);
		pointsToSet.add((AllocNode) allocNode);

		((PointsToSetInternal)ptsProvider.reachingObjectsOfArrayElement(pointsToSet)).forall(new P2SetVisitor() {
			@Override
			public void visit(Node node) {
				if (typeManager.castNeverFails(node.getType(), filteringType))
					ptSet.add((AllocNode)node);
			}
		});

		return (Set<? extends IAllocNode>) ptSet;
	}

	@Override
	public Set<? extends IAllocNode> getPTSet(IAllocNode node, final SootField f) {
		if (f.isStatic()) {
			logger.error("Cannot call getPTSet(node, field) with static field: {}", f);
			droidsafe.main.Main.exit(1);
		}
		final Type filteringType = f.getType();

		final Set<AllocNode> allocNodes = new LinkedHashSet<AllocNode>();

		HashPointsToSet pointsToSet = new HashPointsToSet(node.getType(), ptsProvider);
		pointsToSet.add((AllocNode) node);

		((PointsToSetInternal)ptsProvider.reachingObjects(pointsToSet, f)).forall(new P2SetVisitor() {
			@Override
			public void visit(Node node) {
				if (typeManager.castNeverFails(node.getType(), filteringType))
					allocNodes.add((AllocNode)node);
			}
		}); 

		/*
        PointsToSetInternal bases = (PointsToSetInternal)ptsProvider.getSetFactory().newSet(node.getType(), ptsProvider);
        bases.add(node);

        final PointsToSetInternal pts = ptsProvider.getSetFactory().newSet( 
            (f instanceof SootField) ? ((SootField)f).getType() : null, ptsProvider );
        bases.forall( new P2SetVisitor() {
            public final void visit( Node n ) {
                Node nDotF = ((AllocNode) n).dot( f );
                if(nDotF != null) pts.addAll( nDotF.getP2Set(), null );
            }} );

        //visit internal points to set and grab all allocnodes        
        pts.forall(new P2SetVisitor() {
            public void visit(Node n) {
                allocNodes.add((AllocNode)n);
            }
        });
		 */
		return (Set<? extends IAllocNode>) allocNodes;
	}

	@Override
	public Set<? extends IAllocNode> getPTSet(Value val, Context context) {
		//handle case for insensitive run
		if (k == 0)
			return getPTSetIns(val);

		final Set<AllocNode> allocNodes = new LinkedHashSet<AllocNode>();
		final Type filteringType = val.getType();

		PointsToSetInternal pts = null;

		try {
			if (val instanceof InstanceFieldRef) {
				final InstanceFieldRef ifr = (InstanceFieldRef)val;
				pts = (PointsToSetInternal)ptsProvider.reachingObjects(context, (Local)ifr.getBase(), ifr.getField());
			} else if (val instanceof ArrayRef) {
				ArrayRef arrayRef = (ArrayRef)val;
				pts = (PointsToSetInternal)ptsProvider.reachingObjectsOfArrayElement
						(ptsProvider.reachingObjects(context, (Local)arrayRef.getBase()));
			} else if (val instanceof Local){            
				pts = (PointsToSetInternal)ptsProvider.reachingObjects(context, (Local)val);
			} else if (val instanceof StaticFieldRef) {
				SootField field = ((StaticFieldRef)val).getField();
				pts = (PointsToSetInternal)ptsProvider.reachingObjects(field);
			} else if (val instanceof NullConstant) {
				return allocNodes;
			} else {
				logger.error("Unknown reference type for insenstive search: {} {}", val, val.getClass());
				droidsafe.main.Main.exit(1);
			}

			//visit internal points to set and grab all allocnodes        
			pts.forall(new P2SetVisitor() {
				public void visit(Node n) {
					if (typeManager.castNeverFails(n.getType(), filteringType))
						allocNodes.add((AllocNode)n);
				}
			});

		} catch (Exception e) {
			logger.info("Some sort of error getting context insensitive points to set for {}", val, e);
			//e.printStackTrace();
		}

		return allocNodes;

	}

	@Override
	public void dumpPTA() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dumpPTA(String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dumpPTAForContext(PrintStream file, Context sootContext) {
		// TODO Auto-generated method stub

	}



	private void countNode(Map<SootClass, Integer> nodeCount, AllocNode node) {
		SootClass clz = null;
		if (node.getType() instanceof RefType) {
			clz = ((RefType)node.getType()).getSootClass();
		} else if (node.getType() instanceof ArrayType && 
				((ArrayType)node.getType()).getArrayElementType() instanceof RefType) {
			clz = ((RefType)((ArrayType)node.getType()).getArrayElementType()).getSootClass();
		}

		if (clz != null) {
			if (!nodeCount.containsKey(clz)) {
				nodeCount.put(clz, 0);
			}

			nodeCount.put(clz, nodeCount.get(clz) + 1);
		}
	}

	private Set<SootMethod> callgraphSet = new HashSet<SootMethod>();

	public void dumpTextGraph(String fileName) {
		// TODO Auto-generated method stub
		PrintStream printStream  = null;
		try {
			printStream = new PrintStream(fileName);
		}
		catch (Exception ex) {        
			logger.warn("Cannot dump TextGraph {} ", fileName);
			return;
		}

		callgraphSet.clear(); 

		for (SootMethod entry: Scene.v().getEntryPoints()) {
			/*
               printStream.printf("Entry %s \n", entry.toString());
               Iterator<Edge> iterator = callGraph.edgesOutOf(entry);
               while (iterator.hasNext()) {
               totalIndegree++;
               Edge edge = iterator.next();
               printStream.printf("%d: %s \n", totalIndegree, edge.tgt().toString());
               }
               printStream.printf("\n");
			 */
			dumpTextGraph(entry, printStream, 0);
		}

		printStream.close();
	}

	private static String indentString(int level) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < level; i++) {
			builder.append("  ");
		}
		return builder.toString();
	}

	private void dumpTextGraph(SootMethod caller, PrintStream printStream, int level) {

		String indent = indentString(level);
		caller.getTags();
		printStream.printf("%s %s\n", indent, caller.toString());
		Iterator<Edge> iterator = callGraph.edgesOutOf(caller);
		callgraphSet.add(caller);


		//boolean appClass = caller.getDeclaringClass().isApplicationClass();
		boolean systemApi = API.v().isSystemMethod(caller);

		/*
           printStream.printf("%s Declaring method %s: app %s\n", indent, 
           caller.toString(), systemApi? "False": "True");
		 */

		String subindent = indentString(level+1);
		Set<Object> calleeSet = new HashSet<Object>();

		while (iterator != null && iterator.hasNext()) {
			Edge edge = iterator.next();
			if (!systemApi) {
				List<Stmt> invokeStmtList = SootUtils.getInvokeStatements(caller, edge.tgt());
				for (Stmt stmt: invokeStmtList) {
					if (calleeSet.contains(stmt))
						continue;
					printStream.printf("%s #[%s] ", subindent, stmt);
					SourceLocationTag tag = SootUtils.getSourceLocation(stmt);
					if (tag != null) {
						printStream.printf(": %s", tag.toString());
					}
					printStream.printf("\n");
					calleeSet.add(stmt.toString());
				}
			}

			if (!callgraphSet.contains(edge.tgt())) {
				dumpTextGraph(edge.tgt(), printStream, level+1);
			}
			else {
				//already in the call graph, just print it out
				if (calleeSet.contains(edge.tgt()))
					continue;
				printStream.printf("%s %s\n", subindent, edge.tgt().toString());
				calleeSet.add(edge.tgt());
			}
		}
	}

	/**
	 * Create the bi map of NewExpr <-> AllocNode
	 */
	private void createNewToAllocMap() {
		newToAllocNodeMap = HashBiMap.create();
		allAllocNodes = new LinkedHashSet<AllocNode>();

		Map<SootClass,Integer> nodeCount = new LinkedHashMap<SootClass,Integer>();

		int realSize = 0; 

		for (AllocNode node : ptsProvider.getAllocNodes()) {
			if (!(node instanceof InsensitiveAllocNode)) {
				logger.error("Found non-insensitive node in ptsProvider.getAllocNodes()");
				droidsafe.main.Main.exit(1);
			}

			InsensitiveAllocNode insNode = (InsensitiveAllocNode)node;

			newToAllocNodeMap.put(node.getNewExpr(), insNode);
			realSize ++;
			allAllocNodes.add(node);

			//countNode(nodeCount, node);

			for (Map.Entry<Context, ObjectSensitiveAllocNode> entry : insNode.getContextNodeMap().entrySet()) {
				allAllocNodes.add(entry.getValue());
				//countNode(nodeCount, node);
			}
		}


		System.out.println("Alloc node size (insensitive objects): " + realSize);

		/* used to print a sorted list of alloc nodes created
        Map<SootClass, Integer> sortedNodeCount = SootUtils.sortByValue(nodeCount);
        for (Map.Entry<SootClass, Integer> entry : sortedNodeCount.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey());
        }
		 */
	}

	/**
	 * Run context insensitive spark analysis.
	 */
	void setSparkPointsToAnalysis() {
		logger.info("Starting points-to analysis ...");

		HashMap<String, String> opt = new HashMap<String, String>();
		opt.put("enabled","true");
		opt.put("verbose","false");
		opt.put("ignore-types","false");          
		opt.put("force-gc","false");            
		opt.put("pre-jimplify","false");          
		opt.put("vta","false");                   
		opt.put("rta","false");                   
		opt.put("field-based","false");           




		opt.put("simulate-natives","false");      
		opt.put("simple-edges-bidirectional","false");
		opt.put("on-fly-cg","true");            
		opt.put("simplify-offline","false");    
		opt.put("simplify-sccs","false");        
		opt.put("ignore-types-for-sccs","false");
		opt.put("propagator","worklist");
		opt.put("set-impl","double");
		opt.put("double-set-old","hybrid");         
		opt.put("double-set-new","hybrid");
		opt.put("dump-html","false");           
		opt.put("dump-pag","false");             
		opt.put("dump-solution","false");        
		opt.put("topo-sort","false");           
		opt.put("dump-types","true");             
		opt.put("class-method-var","true");     
		opt.put("dump-answer","false");          
		opt.put("add-tags","false");             
		opt.put("set-mass","false");   
		opt.put("types-for-sites","false");        

		opt.put("merge-stringbuffer", Boolean.toString(Config.v().impreciseStrings));   
		opt.put("string-constants", "true");  

		opt.put("kobjsens", Integer.toString(Config.v().kobjsens));

		opt.put("kobjsens-api-calldepth", Integer.toString(Config.v().apiCallDepth));

		opt.put("kobjsens-app-classes-list", getAppClassesString());

		opt.put("kobjsens-context-for-static-inits", Boolean.toString(Config.v().staticinitcontext));

		opt.put("kobjsens-no-context-list", 
				buildNoContextList());

		opt.put("kobjsens-types-for-context", Boolean.toString(Config.v().typesForContext));       

		if (Config.v().extraArrayContext)
			opt.put("kobjsens-extra-array-context", "true"); 

		StringBuffer limitHeapContext = new StringBuffer();

		if (!"0".equals(optionsInCode.get("kobjsens"))) {
			if (!Config.v().fullContextForGUI) { 
				addGUIClasses(limitHeapContext);
			}

			if (!Config.v().fullContextForStrings) {
				addStringClasses(limitHeapContext);
			} 

			if (Config.v().limitcontextforcomplex) {
				limitComplexity(limitHeapContext);
			}
		}


		logger.info("limit heap context list: {}", limitHeapContext.toString());
		opt.put("kobjsens-limit-heap-context", limitHeapContext.toString());

		//now overwrite options with options that are passed in
		for (Map.Entry<String, String> entry : optionsInCode.entrySet()) {
			opt.put(entry.getKey(), entry.getValue());
		}

		/*
        //some context sensitivity
        opt.put("cs-demand", "false");
        opt.put("lazy-pts", "true");
        opt.put("passes", "10");
        opt.put("traversal", "75000");
		 */

		//set k for the run...
		k = Integer.parseInt(opt.get("kobjsens"));

		SparkTransformer.v().transform("",opt);


		logger.info("[spark] Done!");
	}

	private void limitComplexity(StringBuffer buf) {
		if (buf.length() > 0 && ',' != buf.charAt(buf.length() - 1))
			buf.append(',');               

		for (Map.Entry<Object, MutableInt> entry : IntrospectiveAnalysis.v().getPointedByLocals().entrySet()) {
			//don't limit strings or common android api classes
			if (entry.getKey() instanceof NewExpr) {
				SootClass allocedClass = ((NewExpr)entry.getKey()).getBaseType().getSootClass();
				
				if (allocedClass.getName().startsWith("java.util") ||
						allocedClass.getName().startsWith("java.lang") ||
						allocedClass.getName().startsWith("java.net") ||
						allocedClass.getName().startsWith("java.io") ||
						allocedClass.getName().startsWith("android.os") ||
						allocedClass.getName().startsWith("android.net") ||
						allocedClass.getName().startsWith("android.util") ||
						allocedClass.getName().startsWith("android.content") ||
						allocedClass.getName().startsWith("android.database"))
					continue;
			}
			if (entry.getValue().value() > 100) {
//				logger.info("Adding allocation to limit heap context list by metric: {} {}", entry.getKey(), entry.getValue());
				buf.append(entry.getKey().hashCode() + ",");
			}
		}
	}

	private void addGUIClasses(StringBuffer buf) {
		if (buf.length() > 0 && ',' != buf.charAt(buf.length() - 1))
			buf.append(',');               

		for (SootClass clz : Scene.v().getClasses()) {
			if (clz.isInterface() || clz.isPhantom())
				continue;

			for (SootMethod method : clz.getMethods()) {
				if (!method.isConcrete())
					continue;
				Body body = null;
				try {
					body =  method.retrieveActiveBody();
				} catch (Exception e) {
					body = null;
				}

				if (body == null) continue;

				for (Unit unit  :body.getUnits()) {
					Stmt stmt = (Stmt)unit;
					if (stmt instanceof AssignStmt &&
							((AssignStmt)stmt).getRightOp() instanceof NewExpr) {
						NewExpr newExpr = (NewExpr)((AssignStmt)stmt).getRightOp();
						if (newExpr.getType() instanceof RefType &&
								API.v().isGUIClass(((RefType)newExpr.getType()).getSootClass())) {
							buf.append(newExpr.hashCode() + ",");
//							logger.info("Adding gui allocation to limit heap context list of spark: {} {}", stmt, newExpr.hashCode());
						}					
					}
				}
			}

		}

	}

	private void addStringClasses(StringBuffer buf) {
		if (buf.length() > 0 && ',' != buf.charAt(buf.length() - 1))
			buf.append(',');               

		for (SootClass clz : Scene.v().getClasses()) {
			if (clz.isInterface() || clz.isPhantom())
				continue;

			for (SootMethod method : clz.getMethods()) {
				if (!method.isConcrete())
					continue;
				Body body = null;
				try {
					body =  method.retrieveActiveBody();
				} catch (Exception e) {
					body = null;
				}

				if (body == null) continue;

				for (Unit unit  :body.getUnits()) {
					Stmt stmt = (Stmt)unit;
					if (stmt instanceof AssignStmt &&
							((AssignStmt)stmt).getRightOp() instanceof NewExpr) {
						NewExpr newExpr = (NewExpr)((AssignStmt)stmt).getRightOp();
						if (newExpr.getType() instanceof RefType &&
								SootUtils.isStringOrSimilarType(newExpr.getType())) {
							buf.append(newExpr.hashCode() + ",");
//							logger.info("Adding string allocation to limit heap context list of spark: {} {}", stmt, newExpr.hashCode());
						}					
					}
				}
			}

		}
	}

	private String buildNoContextList() {
		StringBuffer buf = new StringBuffer();
		int i = 0;

		for (String str : NO_CONTEXT) {
			SootClass clz = Scene.v().getSootClass(str);

			for (SootClass child : Scene.v().getActiveHierarchy().getSubclassesOfIncluding(clz)) {
				i++;
				buf.append(child + ",");
//				logger.info("Adding class to ignore context list of spark: {}", child);
			}
		}

		String ret = buf.toString();

		ret = ret.substring(0, ret.length() - 1);

		System.out.println("No context: " + i);

		return ret;

	}

	private boolean isImportantJavaAlloc(SootClass clz) {
		String packageName = clz.getName();

		for (String packPrefix : IMPORTANT_ALLOCATORS_FROM_JAVA) {
			if (packageName.startsWith(packPrefix))
				return true;
		}

		return false;
	}

	private String getAppClassesString() {
		StringBuffer sb = new StringBuffer();

		for (SootClass clz : Scene.v().getClasses()) {
			if (!API.v().isSystemClass(clz) || 
					clz.getName().startsWith(Project.DS_GENERATED_CLASSES_PREFIX) ||
					clz.getName().startsWith("droidsafe")) {
//				logger.info("Adding class to app list of spark: {}", clz);
				sb.append(clz + ",");
			}
		}
		String ret = sb.toString();

		ret = ret.substring(0, ret.length() - 1);

		return ret;
	}

	@Override
	public void dumpCallGraph(String fileStr) {
		// TODO Auto-generated method stub

	}
}
