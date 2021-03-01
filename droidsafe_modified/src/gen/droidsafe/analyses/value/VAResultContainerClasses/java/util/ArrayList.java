package droidsafe.analyses.value.VAResultContainerClasses.java.util;

import droidsafe.analyses.value.RefVAModel;
import java.util.AbstractList;

public class ArrayList<E> extends RefVAModel {

    public ArrayList(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
