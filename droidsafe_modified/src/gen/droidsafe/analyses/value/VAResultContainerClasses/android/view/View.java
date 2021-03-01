package droidsafe.analyses.value.VAResultContainerClasses.android.view;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.VAResultContainerClasses.android.util.FloatProperty;
import droidsafe.analyses.value.VAResultContainerClasses.android.view.AbsSavedState;

public class View extends RefVAModel {

    public View(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }

    public static class AccessibilityDelegate extends RefVAModel {

        public AccessibilityDelegate(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    static class AttachInfo extends RefVAModel {

        public AttachInfo(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }

        interface Callbacks {
        }

        static class InvalidateInfo extends RefVAModel {

            public InvalidateInfo(soot.jimple.toolkits.pta.IAllocNode node) {
                super(node);
            }
        }
    }

    public static class BaseSavedState extends AbsSavedState {

        public BaseSavedState(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    class CheckForLongPress extends RefVAModel {

        public CheckForLongPress(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private final class CheckForTap extends RefVAModel {

        public CheckForTap(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    public static class DragShadowBuilder extends RefVAModel {

        public DragShadowBuilder(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    static class ListenerInfo extends RefVAModel {

        public ListenerInfo(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    public static class MeasureSpec extends RefVAModel {

        public MeasureSpec(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    static class MyFloatPropertyView extends FloatProperty<View> {

        public MyFloatPropertyView(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    public interface OnAttachStateChangeListener {
    }

    public interface OnClickListener {
    }

    public interface OnCreateContextMenuListener {
    }

    public interface OnDragListener {
    }

    public interface OnFocusChangeListener {
    }

    public interface OnGenericMotionListener {
    }

    public interface OnHoverListener {
    }

    public interface OnKeyListener {
    }

    public interface OnLayoutChangeListener {
    }

    public interface OnLongClickListener {
    }

    public interface OnSystemUiVisibilityChangeListener {
    }

    public interface OnTouchListener {
    }

    private final class PerformClick extends RefVAModel {

        public PerformClick(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private static class ScrollabilityCache extends RefVAModel {

        public ScrollabilityCache(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private class SendViewScrolledAccessibilityEvent extends RefVAModel {

        public SendViewScrolledAccessibilityEvent(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    static class TransformationInfo extends RefVAModel {

        public TransformationInfo(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private final class UnsetPressedState extends RefVAModel {

        public UnsetPressedState(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }
}
