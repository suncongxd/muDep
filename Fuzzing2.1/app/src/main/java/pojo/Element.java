package pojo;

public class Element {
    private String type;
    private String name;//实际名称
    private String fullType;//全称类型名
    private Element changeField;

    public Element(String fullType){
        this.fullType = fullType;
    }
    public Element(String name, String fullType){
        this.name = name;
        this.fullType = fullType;
    }

    public Element(String type, String fullType, String name){
        this.type = type;
        this.fullType = fullType;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Element getChangeField() {
        return changeField;
    }

    public void setChangeField(Element changeField) {
        this.changeField = changeField;
    }

    public String getFullType() {
        return fullType;
    }

    public void setFullType(String fullType) {
        this.fullType = fullType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element)) return false;

        Element element = (Element) o;

        if (type != null ? !type.equals(element.type) : element.type != null) return false;
        if (name != null ? !name.equals(element.name) : element.name != null) return false;
        if (fullType != null ? !fullType.equals(element.fullType) : element.fullType != null)
            return false;
        return changeField != null ? changeField.equals(element.changeField) : element.changeField == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fullType != null ? fullType.hashCode() : 0);
        result = 31 * result + (changeField != null ? changeField.hashCode() : 0);
        return result;
    }
}
