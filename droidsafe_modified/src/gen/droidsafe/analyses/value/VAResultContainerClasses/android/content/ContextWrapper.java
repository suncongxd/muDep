package droidsafe.analyses.value.VAResultContainerClasses.android.content;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.VAResultContainerClasses.android.content.Context;

public class ContextWrapper extends Context {

    public ContextWrapper(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
