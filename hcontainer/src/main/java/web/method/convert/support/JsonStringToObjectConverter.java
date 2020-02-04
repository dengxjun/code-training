package web.method.convert.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import web.method.convert.Converter;

import java.io.IOException;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/30
 */
public class JsonStringToObjectConverter<T> implements Converter<String,T>{
    private final Class<T> targetType;

    public JsonStringToObjectConverter(Class<T> targetType) {
        this.targetType = targetType;
    }

    @Override
    public T convert(String source) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        return (T)mapper.readValue(source, targetType);
    }
}
