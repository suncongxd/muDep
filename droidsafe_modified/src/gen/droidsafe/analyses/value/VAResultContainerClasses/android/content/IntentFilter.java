package droidsafe.analyses.value.VAResultContainerClasses.android.content;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.VAResultContainerClasses.android.util.AndroidException;
import droidsafe.analyses.value.primitives.StringVAModel;

public class IntentFilter extends RefVAModel {

    public StringVAModel mActions = new StringVAModel();

    public IntentFilter(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }

    public static final class AuthorityEntry extends RefVAModel {

        public AuthorityEntry(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    public static class MalformedMimeTypeException extends AndroidException {

        public MalformedMimeTypeException(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }
}
