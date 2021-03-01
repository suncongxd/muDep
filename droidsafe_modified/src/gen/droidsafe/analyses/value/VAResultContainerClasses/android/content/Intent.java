package droidsafe.analyses.value.VAResultContainerClasses.android.content;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.primitives.ClassVAModel;
import droidsafe.analyses.value.primitives.StringVAModel;

public class Intent extends RefVAModel {

    public StringVAModel DSmCategories = new StringVAModel();

    public StringVAModel mAction = new StringVAModel();

    public ClassVAModel mClsComponent = new ClassVAModel();

    public StringVAModel mPackage = new StringVAModel();

    public StringVAModel mType = new StringVAModel();

    public Intent(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }

    public static final class FilterComparison extends RefVAModel {

        public FilterComparison(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    public static class ShortcutIconResource extends RefVAModel {

        public ShortcutIconResource(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }
}
