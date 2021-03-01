package droidsafe.analyses.value.VAResultContainerClasses.java.lang;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.primitives.StringVAModel;

public final class ProcessBuilder extends RefVAModel {

    public StringVAModel commands = new StringVAModel();

    public ProcessBuilder(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
