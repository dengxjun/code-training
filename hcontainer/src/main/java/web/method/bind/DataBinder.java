package web.method.bind;

import web.method.convert.ConvertFactory;
import web.method.convert.Converter;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public class DataBinder {

    private Object target;

    private String objectName;

    public DataBinder(String objectName, Object target) {
        this.objectName = objectName;
        this.target = target;
    }

    public <T> T convertIfNecessary(Object value, Class<T> requiredType){
        Converter converter = ConvertFactory.getConverter(requiredType);
        return converter == null ? (T)value : (T)converter.convert(value);
    }
}
