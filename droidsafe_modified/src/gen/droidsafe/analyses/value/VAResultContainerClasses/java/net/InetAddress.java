package droidsafe.analyses.value.VAResultContainerClasses.java.net;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.primitives.StringVAModel;

public class InetAddress extends RefVAModel {

    public StringVAModel hostName = new StringVAModel();

    public InetAddress(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
