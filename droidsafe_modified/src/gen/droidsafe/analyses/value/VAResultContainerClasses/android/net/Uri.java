package droidsafe.analyses.value.VAResultContainerClasses.android.net;

import droidsafe.analyses.value.RefVAModel;
import droidsafe.analyses.value.primitives.StringVAModel;
import java.util.AbstractList;

public class Uri extends RefVAModel {

    // orphaned legacy field 
    public StringVAModel uriString = new StringVAModel();

    public Uri(soot.jimple.toolkits.pta.IAllocNode node) {
        super(node);
    }

    private abstract static class AbstractHierarchicalUri extends Uri {

        public AbstractHierarchicalUri(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    abstract static class AbstractPart extends RefVAModel {

        public AbstractPart(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }

        static class Representation extends RefVAModel {

            public Representation(soot.jimple.toolkits.pta.IAllocNode node) {
                super(node);
            }
        }
    }

    public static final class Builder extends RefVAModel {

        public Builder(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private static class HierarchicalUri extends AbstractHierarchicalUri {

        public HierarchicalUri(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private static class OpaqueUri extends Uri {

        public OpaqueUri(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    static class Part extends AbstractPart {

        public Part(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }

        private static class EmptyPart extends Part {

            public EmptyPart(soot.jimple.toolkits.pta.IAllocNode node) {
                super(node);
            }
        }
    }

    static class PathPart extends AbstractPart {

        public PathPart(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    static class PathSegments extends RefVAModel {

        public PathSegments(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    static class PathSegmentsBuilder extends RefVAModel {

        public PathSegmentsBuilder(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }

    private static class StringUri extends AbstractHierarchicalUri {

        public StringUri(soot.jimple.toolkits.pta.IAllocNode node) {
            super(node);
        }
    }
}
