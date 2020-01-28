package web.method.resolver.support;

import ioc.util.BeanUtils;
import ioc.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.HandlerMapping;
import web.http.constant.HttpConstant;
import web.method.MethodParameter;
import web.method.bind.DataBinder;
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
public class RequestParamMethodParametersResolver implements MethodParametersResolver {
    private static Logger logger = LoggerFactory.getLogger(HandlerMapping.class);

    @Override
    public Object getMethodParametersValue(HttpServletRequest req, MethodParameter parameter) throws Exception{
        String httpMethod = req.getMethod();
        //解析url中参数
        Object obj = req.getParameter(parameter.getFieldName());
        if (!HttpConstant.METHOD_GET.equals(httpMethod) && obj == null){
            //解析body中参数
            obj = rsolveRequestBodyParameters(req, parameter);
        }

//        Object paramInstance = parameter.getType().newInstance();
        DataBinder dataBinder = new DataBinder(parameter.getFieldName(), null);
        obj = dataBinder.convertIfNecessary(obj, parameter.getType());
        return obj;
    }

    @Override
    public boolean supportParameterType(MethodParameter parameter) {
        return BeanUtils.isSimpleProperty(parameter.getType());
    }

    private Object rsolveRequestBodyParameters(HttpServletRequest req, MethodParameter methodParameter) throws IOException {
        Object value = null;
        String body = StrUtil.convertToStringFromInputStream(HttpServletRequestParser.getBody(req));
        logger.debug("request body: {}",body);
        if (StrUtil.isNotEmpty(body)){
            Map<String,List> pvMap = StrUtil.mapRequestBodyString(body);
            value = pvMap.get(methodParameter.getFieldName());
        }
        return value;
    }
}
