package droidsafe.analyses.value.VAResultContainerClasses.android.app;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.VAResultContainerClasses.android.view.ContextThemeWrapper;

public class Activity extends ContextThemeWrapper {

    public Activity(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }

    private static final class ManagedCursor extends RefVAModel {

        public ManagedCursor(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private static class ManagedDialog extends RefVAModel {

        public ManagedDialog(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    static final class NonConfigurationInstances extends RefVAModel {

        public NonConfigurationInstances(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }
}
