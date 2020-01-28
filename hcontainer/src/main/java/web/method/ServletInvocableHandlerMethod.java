package web.method;

import web.method.resolver.MethodParametersResolver;
import web.method.resolver.support.MapMethodParametersResolver;
import web.method.resolver.support.ModleAttributeMethodParameterResolver;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>HandlerMethod 调度类，主要职责有： </p>
 *  1. 获取request参数值
 *  2. 方法调用
 *
 * @author heyman
 * @date 2020/1/28
 */
public class ServletInvocableHandlerMethod{

    private HandlerMethod handlerMethod;

    private List<MethodParametersResolver> methodParametersResolvers = new ArrayList<MethodParametersResolver>(){{
        add(new MapMethodParametersResolver());
        add(new ModleAttributeMethodParameterResolver());
    }};

    public ServletInvocableHandlerMethod(HandlerMethod handlerMethod) {
        this.handlerMethod = handlerMethod;
    }

    public Object invoke(Object obj, Object... args) {
        try {
            Class<?>[] clses = new Class<?>[handlerMethod.getParams().size()];
            handlerMethod.getMethodParams().toArray(clses);
            Method targetMethod = handlerMethod.getTargetClass().getMethod(handlerMethod.getTargetMethodName(), clses);

            return targetMethod.invoke(obj,args);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>obtain parameter value of Controller method from HttpServletRequest</p>
     *  1. get concrete MethodParametersResolver
     *  2. resolver do getMethodParametersValue
     * @param req
     * @param handlerMethod
     * @return
     */
    public Object[] resolveMethodParametersValue(HttpServletRequest req, HandlerMethod handlerMethod) throws Exception{
        List<MethodParameter> params = handlerMethod.getParams();
        Object[] args = new Object[params.size()];
        int index = 0;
        for (MethodParameter methodParameter : params){
            Object obj = null;
            for (MethodParametersResolver resolver : methodParametersResolvers){
                if (resolver.supportParameterType(methodParameter)){
                    obj = resolver.getMethodParametersValue(req, methodParameter);
                }
            }
            args[index] = obj;
            index++;
        }
        return  new Object[0];
    }
}
