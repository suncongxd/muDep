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

package droidsafe.analyses.infoflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;

import soot.Body;
import soot.Local;
import soot.MethodOrMethodContext;
import soot.Scene;
import soot.SootMethod;
import soot.Unit;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.TopologicalOrderer;
import soot.toolkits.graph.Block;
import soot.toolkits.graph.BlockGraph;
import soot.toolkits.graph.DominatorNode;
import soot.util.Chain;
import droidsafe.analyses.pta.PTABridge;
import droidsafe.main.Config;
import droidsafe.utils.SootUtils;

/**
 * This class represents a control flow graph for a whole program.
 */

class SuperControlFlowGraph implements Iterable<Block> {
    private final ObjectUtils objectUtils;
    
    private final List<Block> blocks = new ArrayList<Block>();

    public final Map<SootMethod, List<Block>> methodToBlocks = new HashMap<SootMethod, List<Block>>();
    
    public final Map<Unit, Block> unitToBlock = new HashMap<Unit, Block>();

    public final Map<Local, SootMethod> localToMethod = new HashMap<Local, SootMethod>();
    
    public final Map<Block, Set<DominatorNode>> pdmap = new HashMap<Block, Set<DominatorNode>>();
    
    @Override
    public Iterator<Block> iterator() {
        return this.blocks.iterator();
    }

    public SuperControlFlowGraph(ObjectUtils objectUtils) {
        this.objectUtils = objectUtils;
        collectSuperControlFlowGraphs();
    }

    private void collectSuperControlFlowGraphs() {
        Set<SootMethod> reachableMethods = PTABridge.v().getReachableMethods();
        CallGraph callGraph = Scene.v().getCallGraph();
        TopologicalOrderer topologicalOrderer = new TopologicalOrderer(callGraph);
        topologicalOrderer.go();
        List<MethodOrMethodContext> topologicallyOrderedMethods = topologicalOrderer.order();
        List<MethodOrMethodContext> reverseTopologicallyOrderedMethods = Lists.reverse(topologicallyOrderedMethods);
        for (MethodOrMethodContext methodContext : reverseTopologicallyOrderedMethods) {
            SootMethod method = methodContext.method();
            if (this.methodToBlocks.containsKey(method)) {
                continue;
            }
            if (reachableMethods.contains(method)) {
                if (method.hasActiveBody() && !(SootUtils.isRuntimeStubMethod(method)) && !(this.objectUtils.isAddTaint(method)) && !(this.objectUtils.isGetTaint(method))) {
                    Body body = method.getActiveBody();
                    BlockGraph blockGraph = new InfoBriefBlockGraph(body);
                    List<Block> blocks = blockGraph.getBlocks();
                    this.blocks.addAll(blocks);
                    this.methodToBlocks.put(method, blocks);
                    for (Block block : blockGraph) {
                        Iterator<Unit> units = block.iterator();
                        while (units.hasNext()) {
                            Unit unit = units.next();
                            this.unitToBlock.put(unit, block);
                        }
                    }
                }
            }
        }
        if (Config.v().infoFlowValues != null) {
            for (SootMethod method : reachableMethods) {
                if (method.hasActiveBody()) {
                    Body body = method.getActiveBody();
                    Chain<Local> locals = body.getLocals();
                    for (Local local : locals) {
                        this.localToMethod.put(local, method);
                    }
                }
            }
        }
    }
}
