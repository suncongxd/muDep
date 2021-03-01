package droidsafe.analyses.value.VAResultContainerClasses.java.net;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.primitives.IntVAModel;
import droidsafe.analyses.value.primitives.StringVAModel;
import java.net.SocketAddress;

public class InetSocketAddress extends RefVAModel {

    public StringVAModel hostname = new StringVAModel();

    public IntVAModel port = new IntVAModel();

    public InetSocketAddress(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
