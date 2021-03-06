package ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 邓小军
 * @since: 2020/1/19 14:11
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Component
public @interface Controller {
    String value() default "";
    String aias() default "";
}
