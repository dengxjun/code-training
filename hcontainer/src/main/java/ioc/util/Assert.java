package ioc.util;

import java.util.List;
import java.util.Locale;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public class Assert {

    public static void notNull(Object obj, String msg) {
        if (obj == null){
            throw new RuntimeException(msg);
        }
    }

    public static void notEmpty(List<Locale> locales, String msg) {
        if (!CollectionUtils.isNotEmpty(locales)){
            throw new RuntimeException(msg);
        }
    }

    public static void isInstanceOf(Class cls, Object key, String msg) {
        if (cls.isInstance(key)){
            throw new RuntimeException(msg);
        }
    }
}
