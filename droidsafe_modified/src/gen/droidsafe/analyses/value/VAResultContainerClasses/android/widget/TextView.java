package droidsafe.analyses.value.VAResultContainerClasses.android.widget;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.VAResultContainerClasses.android.os.Handler;
import droidsafe.analyses.value.VAResultContainerClasses.android.view.View;
import droidsafe.analyses.value.VAResultContainerClasses.android.view.View.BaseSavedState;
import droidsafe.analyses.value.VAResultContainerClasses.android.view.ViewTreeObserver.OnTouchModeChangeListener;
import droidsafe.analyses.value.VAResultContainerClasses.android.widget.BaseAdapter;
import droidsafe.analyses.value.VAResultContainerClasses.android.widget.PopupWindow;
import droidsafe.analyses.value.primitives.StringVAModel;

public class TextView extends View {

    public StringVAModel mHint = new StringVAModel();

    public StringVAModel mText = new StringVAModel();

    public TextView(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }

    private class ActionPopupWindow extends PinnedPopupWindow {

        public ActionPopupWindow(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private static class Blink extends Handler {

        public Blink(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    public enum BufferType {

        NORMAL, SPANNABLE, EDITABLE
    }

    private class ChangeWatcher extends RefVAModel {

        public ChangeWatcher(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private static class CharWrapper extends RefVAModel {

        public CharWrapper(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private class CorrectionHighlighter extends RefVAModel {

        public CorrectionHighlighter(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private interface CursorController extends OnTouchModeChangeListener {
    }

    private static class DragLocalState extends RefVAModel {

        public DragLocalState(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    static class Drawables extends RefVAModel {

        public Drawables(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private class EasyEditPopupWindow extends PinnedPopupWindow {

        public EasyEditPopupWindow(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private class EasyEditSpanController extends RefVAModel {

        public EasyEditSpanController(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private static class ErrorPopup extends PopupWindow {

        public ErrorPopup(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private abstract class HandleView extends View {

        public HandleView(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    static class InputContentType extends RefVAModel {

        public InputContentType(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    static class InputMethodState extends RefVAModel {

        public InputMethodState(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private class InsertionHandleView extends HandleView {

        public InsertionHandleView(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private class InsertionPointCursorController extends RefVAModel {

        public InsertionPointCursorController(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private static final class Marquee extends Handler {

        public Marquee(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    public interface OnEditorActionListener {
    }

    private abstract class PinnedPopupWindow extends RefVAModel {

        public PinnedPopupWindow(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private class PositionListener extends RefVAModel {

        public PositionListener(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    public static class SavedState extends BaseSavedState {

        public SavedState(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private class SelectionActionModeCallback extends RefVAModel {

        public SelectionActionModeCallback(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private class SelectionEndHandleView extends HandleView {

        public SelectionEndHandleView(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private class SelectionModifierCursorController extends RefVAModel {

        public SelectionModifierCursorController(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private class SelectionStartHandleView extends HandleView {

        public SelectionStartHandleView(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private class SuggestionsPopupWindow extends PinnedPopupWindow {

        public SuggestionsPopupWindow(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }

        private class CustomPopupWindow extends PopupWindow {

            public CustomPopupWindow(soot.jimple.toolkits.pta.IAllocNode node) {
                super(node);
            }
        }

        private class SuggestionAdapter extends BaseAdapter {

            public SuggestionAdapter(soot.jimple.toolkits.pta.IAllocNode node) {
                super(node);
            }
        }

        private class SuggestionInfo extends RefVAModel {

            public SuggestionInfo(soot.jimple.toolkits.pta.IAllocNode node) {
                super(node);
            }
        }

        private class SuggestionSpanComparator extends RefVAModel {

            public SuggestionSpanComparator(soot.jimple.toolkits.pta.IAllocNode node) {
                super(node);
            }
        }
    }

    private static enum TextAlign {

        INHERIT, GRAVITY, TEXT_START, TEXT_END, CENTER, VIEW_START, VIEW_END
    }

    private interface TextViewPositionListener {
    }
}
