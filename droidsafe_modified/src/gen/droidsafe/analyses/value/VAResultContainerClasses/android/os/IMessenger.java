package droidsafe.analyses.value.VAResultContainerClasses.android.os;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.VAResultContainerClasses.android.os.Binder;
import droidsafe.analyses.value.VAResultContainerClasses.android.os.IInterface;

public interface IMessenger extends IInterface {

    public abstract static class Stub extends Binder {

        public Stub(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }

        private static class Proxy extends RefVAModel {

            public Proxy(soot.jimple.toolkits.pta.IAllocNode node) {
                super(node);
            }
        }
    }
}
