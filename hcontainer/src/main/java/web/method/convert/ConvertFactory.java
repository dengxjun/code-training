package web.method.convert;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public class ConvertFactory {
    public static <T> Converter<String, T> getConverter(Class<T> targetType) {
        if (Number.class.isAssignableFrom(targetType)){
            return new StringToNumberConverter(targetType);
        }else {
            return null;
        }
    }
}
