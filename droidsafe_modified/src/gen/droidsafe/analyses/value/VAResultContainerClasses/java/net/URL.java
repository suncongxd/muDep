package droidsafe.analyses.value.VAResultContainerClasses.java.net;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.primitives.StringVAModel;

public final class URL extends RefVAModel {

    public StringVAModel authority = new StringVAModel();

    public StringVAModel file = new StringVAModel();

    public StringVAModel host = new StringVAModel();

    public transient StringVAModel path = new StringVAModel();

    public StringVAModel protocol = new StringVAModel();

    public transient StringVAModel query = new StringVAModel();

    public StringVAModel ref = new StringVAModel();

    public transient StringVAModel userInfo = new StringVAModel();

    public URL(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
