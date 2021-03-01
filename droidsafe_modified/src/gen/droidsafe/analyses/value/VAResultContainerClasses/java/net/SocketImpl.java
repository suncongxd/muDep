package droidsafe.analyses.value.VAResultContainerClasses.java.net;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.primitives.IntVAModel;

public abstract class SocketImpl extends RefVAModel {

    public IntVAModel localport = new IntVAModel();

    public IntVAModel port = new IntVAModel();

    public SocketImpl(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
