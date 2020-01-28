package web.method.resolver.support;

import web.method.MethodParameter;
import web.method.resolver.MethodParametersResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public class MapMethodParametersResolver implements MethodParametersResolver{
    @Override
    public Object getMethodParametersValue(HttpServletRequest req, MethodParameter parameter)  throws Exception{
        return new Object();
    }

    @Override
    public boolean supportParameterType(MethodParameter parameter) {
        return false;
    }
}
