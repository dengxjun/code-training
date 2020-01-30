package web.method.convert.support;

import ioc.util.NumberUtils;
import web.method.convert.Converter;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public class StringToNumberConverter<T extends Number> implements Converter<String, T> {
    private final Class<T> targetType;

    public StringToNumberConverter(Class<T> targetType) {
        this.targetType = targetType;
    }

    @Override
    public T convert(String source) {
        if (source.length() == 0) {
            return null;
        }
        return NumberUtils.parseNumber(source, this.targetType);
    }
}
