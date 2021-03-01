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

import java.util.Set;

import soot.Type;
import soot.Value;
import soot.jimple.InvokeExpr;
import soot.jimple.toolkits.callgraph.Edge;
import soot.jimple.toolkits.pta.IAllocNode;

/**
 * Interface for providing PTA information for a method.  
 * 
 * @author mgordon
 *
 */
public interface PTAMethodInformation {
    /**
     * Return the argument expression for the call to the API method.
     */
    public Value getArgValue(int i);

    /**
     * Return true if arg i value is a pointer in the PT graph.
     */
    public boolean isArgPointer(int i);
    
    /**
     * Return the points to set of the receiver (if it exists) in the context of this
     * output event.
     */
    public Set<IAllocNode> getReceiverPTSet();

    /**
     * Return the type of the receiver value.
     */
    public Type getReceiverType();
    
    /**
     * Return the type of the value for the argument, does not look 
     * at the points to set.
     */
    public Type getArgumentType(int i);

    /**
     * Return the points to set for the pointer argument at index i.
     */
    public Set<? extends IAllocNode> getArgPTSet(int i);
    
    /** 
     * Return the number of args in the invoke expression.
     */
    public int getNumArgs();

    /**
     * Return true if the method call has a receiver.
     * @return
     */
    public boolean hasReceiver();


    /**
     * Return the value of the receiver, probably a field or local variable.
     */
    public Value getReceiver();

    /**
     * Return true if the receiver is a pointer. 
     */
    public boolean isReceiverPointer();
    
    /**
     * Return the invoke expression that invokes the method.
     */
    public InvokeExpr getInvokeExpr();
    
    /**
     * Return the context for sensitive lookups. 
     */
    public Edge getEdge();
}
