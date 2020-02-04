package web.method.resolver.support;

import ioc.util.BeanUtils;
import ioc.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.HandlerMapping;
import web.http.constant.HttpConstant;
import web.method.MethodParameter;
import web.method.bind.DataBinder;
import web.method.resolver.MethodParameterRequestProcessor;
import web.method.resolver.MethodParametersResolver;
import web.http.HttpServletRequestParser;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public class RequestParamMethodParametersResolver extends MethodParameterRequestProcessor implements MethodParametersResolver {
    private static Logger logger = LoggerFactory.getLogger(RequestParamMethodParametersResolver.class);

    @Override
    public Object getMethodParametersValue(HttpServletRequest req, MethodParameter parameter) throws Exception{
        Object obj = rsolveRequestBodyParameters(req, parameter);
        DataBinder dataBinder = new DataBinder(parameter.getFieldName());
        obj = dataBinder.convertIfNecessary(obj, parameter.getType());
        return obj;
    }

    @Override
    public boolean supportParameterType(MethodParameter parameter) {
        return BeanUtils.isSimpleProperty(parameter.getType());
    }


}
