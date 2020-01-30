package web.method.convert;

import web.http.constant.HttpConstant;
import web.method.convert.support.JsonStringToObjectConverter;
import web.method.convert.support.StringToNumberConverter;
import web.method.convert.support.XmlStringToObjectConverter;

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

    public static <T> Converter<String, T> getConverter(String contentType, Class<T> targetType) {
        if (HttpConstant.APPLICATION_JSON_TYPE.equals(contentType)){
            return new JsonStringToObjectConverter<>(targetType);
        } else if (HttpConstant.APPLICATION_XML_TYPE.equals(contentType)){
            return new XmlStringToObjectConverter<>(targetType);
        }else{
            return null;
        }
    }
}
