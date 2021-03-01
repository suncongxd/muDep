package droidsafe.analyses.value.VAResultContainerClasses.java.lang.reflect;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.primitives.ClassVAModel;
import java.lang.reflect.AccessibleObject;

public final class Constructor<T> extends RefVAModel {

    public ClassVAModel declaringClass = new ClassVAModel();

    public Constructor(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
