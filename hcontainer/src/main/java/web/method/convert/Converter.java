package web.method.convert;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public interface Converter<S, T> {
    T convert(S source);
}
