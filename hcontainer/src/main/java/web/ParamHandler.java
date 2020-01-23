package web;

/**
 * @author: 邓小军
 * @since: 2020/1/20 16:23
 *
 */
public class ParamHandler {

    private Class type;

    private String fieldName;

    public ParamHandler(Class type, String fieldName) {
        this.type = type;
        this.fieldName = fieldName;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
