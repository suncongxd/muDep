package droidsafe.analyses.value.VAResultContainerClasses.android.view;

import droidsafe.analyses.value.RefVAModel;

public abstract class AbsSavedState extends RefVAModel {

    public AbsSavedState(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
