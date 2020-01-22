package ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/19 14:11
 * @Description:
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD,ElementType.TYPE})
public @interface RequestMapping {
    String value();
    RequestMethod method() default RequestMethod.GET;
    String aias() default "";
}
