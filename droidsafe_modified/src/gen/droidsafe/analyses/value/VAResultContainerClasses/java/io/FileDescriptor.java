package droidsafe.analyses.value.VAResultContainerClasses.java.io;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.primitives.IntVAModel;

public final class FileDescriptor extends RefVAModel {

    public IntVAModel descriptor = new IntVAModel();

    public FileDescriptor(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
