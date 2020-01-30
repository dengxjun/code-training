package web.method.convert.support;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import web.method.convert.Converter;

import java.io.IOException;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/30
 */
public class XmlStringToObjectConverter<T> implements Converter<String,T>{
    private final Class<T> targetType;

    public XmlStringToObjectConverter(Class<T> targetType) {
        this.targetType = targetType;
    }

    @Override
    public T convert(String source) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDefaultUseWrapper(false);
        //自动忽略无法对应pojo的字段
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (T)xmlMapper.readValue(source, targetType);
    }
}
