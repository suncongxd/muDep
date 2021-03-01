package droidsafe.analyses.value.VAResultContainerClasses.java.io;

import droidsafe.analyses.value.RefVAModel;
import java.io.InputStream;

public class FileInputStream extends RefVAModel {

    public FileInputStream(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
