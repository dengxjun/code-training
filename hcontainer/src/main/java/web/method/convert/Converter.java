package web.method.convert;

import java.io.IOException;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public interface Converter<S, T> {
    T convert(S source) throws IOException;
}
