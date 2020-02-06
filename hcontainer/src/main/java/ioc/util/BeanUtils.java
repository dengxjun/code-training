package ioc.util;

import ioc.annotation.Controller;
import org.apache.commons.lang3.ClassUtils;

import java.lang.annotation.Annotation;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.Locale;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public class BeanUtils {
    /**
     * Check if the given type represents a "simple" property:
     * a primitive, a String or other CharSequence, a Number, a Date,
     * a URI, a URL, a Locale, a Class, or a corresponding array.
     * <p>Used to determine properties to check for a "simple" dependency-check.
     * @param clazz the type to check
     * @return whether the given type represents a "simple" property
     */
    public static boolean isSimpleProperty(Class<?> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        return isSimpleValueType(clazz) || (clazz.isArray() && isSimpleValueType(clazz.getComponentType()));
    }

    /**
     * Check if the given type represents a "simple" value type:
     * a primitive, a String or other CharSequence, a Number, a Date,
     * a URI, a URL, a Locale or a Class.
     * @param clazz the type to check
     * @return whether the given type represents a "simple" value type
     */
    public static boolean isSimpleValueType(Class<?> clazz) {
        return ClassUtils.isPrimitiveOrWrapper(clazz) || clazz.isEnum() ||
                CharSequence.class.isAssignableFrom(clazz) ||
                Number.class.isAssignableFrom(clazz) ||
                Date.class.isAssignableFrom(clazz) ||
                clazz.equals(URI.class) || clazz.equals(URL.class) ||
                clazz.equals(Locale.class) || clazz.equals(Class.class);
    }

    public static boolean containAnnotation(Class targetClass, Class<Controller> controllerClass) {
        Annotation[] annotations = targetClass.getAnnotations();
        for (Annotation ann : annotations) {
            if (controllerClass.isAssignableFrom(ann.annotationType())){
                return true;
            }
        }
        return false;
    }
}
