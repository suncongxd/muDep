package droidsafe.analyses.value.VAResultContainerClasses.android.os;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.VAResultContainerClasses.android.os.IMessenger.Stub;

public class Handler extends RefVAModel {

    public Handler(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }

    public interface Callback {
    }

    private final class MessengerImpl extends Stub {

        public MessengerImpl(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }
}
