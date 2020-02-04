package web.method.resolver.support;

import ioc.annotation.RequestBody;
import ioc.util.StrUtil;
import web.http.HttpServletRequestParser;
import web.http.constant.HttpConstant;
import web.method.MethodParameter;
import web.method.convert.ConvertFactory;
import web.method.convert.Converter;
import web.method.resolver.MethodParametersResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/30
 */
public class RequestBodyMethodParameterResolver implements MethodParametersResolver {
    @Override
    public Object getMethodParametersValue(HttpServletRequest req, MethodParameter parameter) throws Exception {
        String body = StrUtil.convertToStringFromInputStream(HttpServletRequestParser.getBody(req));
        String contentType = req.getContentType();
        Converter converter = ConvertFactory.getConverter(contentType, parameter.getType());
        return converter.convert(body);
    }

    @Override
    public boolean supportParameterType(MethodParameter parameter) {
        return  parameter.getAnnotationType(RequestBody.class) != null;
    }
}
