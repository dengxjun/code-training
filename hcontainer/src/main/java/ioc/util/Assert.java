package ioc.util;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public class Assert {
    public static void notNull(Class<?> clazz, String msg) {
        if (clazz == null){
            throw new RuntimeException(msg);
        }
    }

    public static void notNull(Number number, String msg) {
        if (number == null){
            throw new RuntimeException(msg);
        }
    }

    public static void notNull(String str, String msg) {
        if (str == null){
            throw new RuntimeException(msg);
        }
    }
}
