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

package droidsafe.analyses.cg;

import org.jgrapht.graph.DefaultEdge;

import soot.jimple.Stmt;

public class StmtEdge<V> extends DefaultEdge {
    /**
     * 
     */
    private static final long serialVersionUID = -2770165153083926512L;
    private Stmt stmt;
    private V v1;
    private V v2;
    private boolean throughAPI;
    
    public StmtEdge(V v1, V v2, Stmt stmt, boolean throughAPI) {
        super();
        this.v1 = v1;
        this.v2 = v2;
        this.stmt = stmt;
        this.throughAPI = throughAPI;
    }

    public Stmt getStmt() {
        return stmt;
    }
    
    public V getV1() {
        return v1;
    }

    public V getV2() {
        return v2;
    }
    
    public void setThroughAPI(boolean b) {
        throughAPI = b;
    }
    
    public boolean isThroughAPI() {
        return throughAPI;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((stmt == null) ? 0 : stmt.hashCode());
        result = prime * result + (throughAPI ? 1231 : 1237);
        result = prime * result + ((v1 == null) ? 0 : v1.hashCode());
        result = prime * result + ((v2 == null) ? 0 : v2.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        StmtEdge other = (StmtEdge) obj;
        if (stmt == null) {
            if (other.stmt != null) return false;
        } else if (!stmt.equals(other.stmt)) return false;
        if (throughAPI != other.throughAPI) return false;
        if (v1 == null) {
            if (other.v1 != null) return false;
        } else if (!v1.equals(other.v1)) return false;
        if (v2 == null) {
            if (other.v2 != null) return false;
        } else if (!v2.equals(other.v2)) return false;
        return true;
    }
    
    public String toString() {
        return String.format("%s -> %s (%s, throughAPI = %b)", v1, v2, stmt, throughAPI);
    }
    
}
