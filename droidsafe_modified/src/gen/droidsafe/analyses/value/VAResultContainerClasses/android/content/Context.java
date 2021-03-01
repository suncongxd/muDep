package droidsafe.analyses.value.VAResultContainerClasses.android.content;

import droidsafe.analyses.value.RefVAModel;

public abstract class Context extends RefVAModel {

    public Context(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
