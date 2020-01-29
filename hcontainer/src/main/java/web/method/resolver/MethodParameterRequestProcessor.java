package web.method.resolver;

import ioc.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.HandlerMapping;
import web.http.HttpServletRequestParser;
import web.http.constant.HttpConstant;
import web.method.MethodParameter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/29
 */
public class MethodParameterRequestProcessor {
    private static Logger logger = LoggerFactory.getLogger(MethodParameterRequestProcessor.class);

    protected Object rsolveRequestBodyParameters(HttpServletRequest req, MethodParameter methodParameter) throws IOException {
        String httpMethod = req.getMethod();
        Object obj = req.getParameter(methodParameter.getFieldName());
        if (!HttpConstant.METHOD_GET.equals(httpMethod) && obj == null){
            //解析body中参数
            String body = StrUtil.convertToStringFromInputStream(HttpServletRequestParser.getBody(req));
            logger.debug("request body: {}",body);
            if (StrUtil.isNotEmpty(body)){
                Map<String,List> pvMap = StrUtil.mapRequestBodyString(body);
                obj = pvMap.get(methodParameter.getFieldName());
            }
        }
        return obj;
    }

    protected Map<String, String[]> rsolveRequestBodyParametersMap(HttpServletRequest req) throws IOException {
        Map<String, String[]> result = req.getParameterMap();
        return result;
    }
}
