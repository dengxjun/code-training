package ioc.annotation;

import java.lang.annotation.*;

/**
 * @author: 邓小军
 * @since: 2020/1/19 14:11
 *
 */

@Documented
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
    String value() default "";
}
