package droidsafe.analyses.value.VAResultContainerClasses.org.apache.http.client.methods;

import droidsafe.analyses.value.RefVAModel;
import org.apache.http.message.AbstractHttpMessage;

public abstract class HttpRequestBase extends RefVAModel {

    public HttpRequestBase(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
