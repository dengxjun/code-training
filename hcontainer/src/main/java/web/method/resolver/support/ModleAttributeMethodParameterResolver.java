package web.method.resolver.support;

import ioc.util.BeanUtils;
import web.method.MethodParameter;
import web.method.bind.DataBinder;
import web.method.resolver.MethodParameterRequestProcessor;
import web.method.resolver.MethodParametersResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public class ModleAttributeMethodParameterResolver extends MethodParameterRequestProcessor implements MethodParametersResolver{

    @Override
    public Object getMethodParametersValue(HttpServletRequest req, MethodParameter parameter)  throws Exception{
        Map<String, String[]> stringMap = rsolveRequestBodyParametersMap(req);
        Object paramInstance = parameter.getType().newInstance();
        DataBinder dataBinder = new DataBinder(parameter.getFieldName(),paramInstance);
        dataBinder.doBind(stringMap);
        return dataBinder.getTarget();
    }

    @Override
    public boolean supportParameterType(MethodParameter parameter) {
        return  !BeanUtils.isSimpleProperty(parameter.getType());
    }
}
