package droidsafe.analyses.value.VAResultContainerClasses.java.lang.reflect;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.primitives.ClassVAModel;
import droidsafe.analyses.value.primitives.StringVAModel;
import java.lang.reflect.AccessibleObject;

public final class Method extends RefVAModel {

    public ClassVAModel declaringClass = new ClassVAModel();

    public StringVAModel dsDeclaringClassName = new StringVAModel();

    public StringVAModel name = new StringVAModel();

    public ClassVAModel returnType = new ClassVAModel();

    public Method(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }
}
