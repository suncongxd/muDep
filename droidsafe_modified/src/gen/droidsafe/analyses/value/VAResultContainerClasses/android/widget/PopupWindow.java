package droidsafe.analyses.value.VAResultContainerClasses.android.widget;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.VAResultContainerClasses.android.widget.FrameLayout;

public class PopupWindow extends RefVAModel {

    public PopupWindow(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }

    public interface OnDismissListener {
    }

    private class PopupViewContainer extends FrameLayout {

        public PopupViewContainer(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }
}
