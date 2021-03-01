package droidsafe.analyses.value.VAResultContainerClasses.android.view;

import droidsafe.analyses.value.RefVAModel;

public final class ViewTreeObserver extends RefVAModel {

    public ViewTreeObserver(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }

    public static final class InternalInsetsInfo extends RefVAModel {

        public InternalInsetsInfo(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    public interface OnComputeInternalInsetsListener {
    }

    public interface OnGlobalFocusChangeListener {
    }

    public interface OnGlobalLayoutListener {
    }

    public interface OnPreDrawListener {
    }

    public interface OnScrollChangedListener {
    }

    public interface OnTouchModeChangeListener {
    }

    public interface OnWindowAttachListener {
    }

    public interface OnWindowFocusChangeListener {
    }
}
