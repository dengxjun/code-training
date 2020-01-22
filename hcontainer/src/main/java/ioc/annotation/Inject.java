package ioc.annotation;

import java.lang.annotation.*;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/19 14:11
 * @Description:
 */

@Documented
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
    String value() default "";
}
