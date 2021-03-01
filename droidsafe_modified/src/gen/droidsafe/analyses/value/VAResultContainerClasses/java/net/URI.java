package droidsafe.analyses.value.VAResultContainerClasses.java.net;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.primitives.StringVAModel;
import libcore.net.UriCodec;

public final class URI extends RefVAModel {

    public StringVAModel string = new StringVAModel();

    public URI(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }

    private static class PartEncoder extends RefVAModel {

        public PartEncoder(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }
}
