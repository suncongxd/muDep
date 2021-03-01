package droidsafe.analyses.value.VAResultContainerClasses.android.widget;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.VAResultContainerClasses.android.view.ViewGroup;
import droidsafe.analyses.value.VAResultContainerClasses.android.view.ViewGroup.MarginLayoutParams;

public class FrameLayout extends ViewGroup {

    public FrameLayout(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }

    public static class LayoutParams extends MarginLayoutParams {

        public LayoutParams(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }
}
