package droidsafe.analyses.value.VAResultContainerClasses.java.lang;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.primitives.StringVAModel;

public final class Class<T> extends RefVAModel {

    public StringVAModel dsClassName = new StringVAModel();

    public Class(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
