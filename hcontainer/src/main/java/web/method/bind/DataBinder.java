package web.method.bind;

import ioc.util.StrUtil;
import web.method.convert.ConvertFactory;
import web.method.convert.Converter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public class DataBinder {

    private Object target;

    private String objectName;

    public DataBinder(String objectName) {
        this.objectName = objectName;
    }

    public DataBinder(String objectName, Object target) {
        this.objectName = objectName;
        this.target = target;
    }

    public <T> T convertIfNecessary(Object value, Class<T> requiredType) throws IOException {
        Converter converter = ConvertFactory.getConverter(requiredType);
        return converter == null ? (T)value : (T)converter.convert(value);
    }

    public Object getTarget() {
        return target;
    }

    /**
     * 步骤：<br/>
     * 1. 遍历值map,通过key拼接set方法名称
     * 2. 通过反射获取target所有方法
     * 3. 遍历方法，通过拼接到的方法名称匹配，得到目标方法
     * 4. 获取目标方法参数类型
     * 5. 将map values 转换为对应类型
     * @param obj
     */
    public void doBind(Object obj) throws Exception{
        if (Map.class.isAssignableFrom(obj.getClass())){
            Map<String, String[]> stringMap = (Map<String, String[]>) obj;
            for (Map.Entry<String, String[]> entry : stringMap.entrySet()){
                String key = entry.getKey();
                String[] value = entry.getValue();
                String methodName = StrUtil.getSetterMethodName(key);
                Method[] methods = target.getClass().getMethods();
                for (Method method : methods){
                    if (method.getName().equals(methodName)){
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes.length != 1){
                            continue;
                        }
                        Class<?> parameterType = parameterTypes[0];
                        Object argValue = convertIfNecessary(value[0],parameterType);
                        method.invoke(target,argValue);
                    }
                }
            }
        }
    }
}
