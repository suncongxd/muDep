package droidsafe.analyses.value.VAResultContainerClasses.java.io;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.primitives.StringVAModel;

public class File extends RefVAModel {

    public StringVAModel name = new StringVAModel();

    public StringVAModel path = new StringVAModel();

    public File(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
