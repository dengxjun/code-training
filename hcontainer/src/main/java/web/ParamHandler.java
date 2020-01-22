package web;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/20 16:23
 * @Description:
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
