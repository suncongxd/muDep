package droidsafe.analyses.value.VAResultContainerClasses.android.util;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.VAResultContainerClasses.android.util.Property;

public abstract class FloatProperty<T> extends Property<T, Float> {

    public FloatProperty(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
