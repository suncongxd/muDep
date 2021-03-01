package droidsafe.analyses.value.VAResultContainerClasses.java.io;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.primitives.IntVAModel;
import java.io.OutputStream;

public class FileOutputStream extends RefVAModel {

    public IntVAModel MODE = new IntVAModel();

    public FileOutputStream(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
