package droidsafe.analyses.value.VAResultContainerClasses.android.view;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.VAResultContainerClasses.android.view.View;

public abstract class ViewGroup extends View {

    public ViewGroup(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }

    private static final class HoverTarget extends RefVAModel {

        public HoverTarget(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    public static class LayoutParams extends RefVAModel {

        public LayoutParams(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    public static class MarginLayoutParams extends ViewGroup.LayoutParams {

        public MarginLayoutParams(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private class MyTransitionListener extends RefVAModel {

        public MyTransitionListener(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    public interface OnHierarchyChangeListener {
    }

    private static final class TouchTarget extends RefVAModel {

        public TouchTarget(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }
}
