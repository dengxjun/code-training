package web.method;

import ioc.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

/**
 * @author: 邓小军
 * @since: 2020/1/20 16:23
 *
 */
public class MethodParameter {

    private Class type;

    private String fieldName;

    private Parameter parameter;

    public MethodParameter(Class type, String fieldName) {
        this.type = type;
        this.fieldName = fieldName;
    }

    public MethodParameter(Class type, String fieldName,Parameter parameter) {
        this.parameter = parameter;
        this.fieldName = fieldName;
        this.type = type;
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

    public Annotation getAnnotationType(Class targetAnnotation){
        Annotation[] annotations = parameter.getAnnotations();
        if (annotations.length == 0){
            return null;
        }

        for (Annotation annotation : annotations){
            if (targetAnnotation.isInstance(annotation)){
                return annotation;
            }
        }
        return null;
    }
}
