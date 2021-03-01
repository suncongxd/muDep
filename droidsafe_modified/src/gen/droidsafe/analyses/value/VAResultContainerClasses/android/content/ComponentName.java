package droidsafe.analyses.value.VAResultContainerClasses.android.content;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.primitives.StringVAModel;

public final class ComponentName extends RefVAModel {

    public StringVAModel mClass = new StringVAModel();

    public StringVAModel mPackage = new StringVAModel();

    public ComponentName(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
