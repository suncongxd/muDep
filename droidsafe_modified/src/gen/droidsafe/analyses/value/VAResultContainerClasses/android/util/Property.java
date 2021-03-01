package droidsafe.analyses.value.VAResultContainerClasses.android.util;

import droidsafe.analyses.value.RefVAModel;

public abstract class Property<T, V> extends RefVAModel {

    public Property(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
