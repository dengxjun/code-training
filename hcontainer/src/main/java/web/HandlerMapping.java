package web;

import ioc.annotation.Controller;
import ioc.annotation.RequestMapping;
import ioc.factory.AnnotationConfigBeanFactory;
import ioc.factory.BeanDefinition;
import ioc.util.BeanUtils;
import ioc.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.method.HandlerMethod;
import web.method.MethodParameter;
import web.http.HttpServletRequestParser;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 邓小军
 * @since: 2020/1/20 16:09
 *
 */
public class HandlerMapping {
    private static Logger logger = LoggerFactory.getLogger(HandlerMapping.class);

    private Map<String, HandlerMethod> urlHanderMap = new HashMap<>();

    private WebApplicationContext webApplicationContext;

    /**
     * <p>初始化handlerMapping对象，步骤：
     * <p>
     *     1. 获取所有被@Controller标注过的类的BeanDefinition <br>
     *     2. 解析BeanDefinition对象 <br>
     *     2.1. 获取类级别RequestMapping信息 <br>
     *     2.2. 获取方法级别RequestMapping信息 <br>
     *     2.3. 构造MethodHandler对象 <br>
     *     2.4. 拼接path后保存path，methodHandler到map中 <br>
     * @param wac WebApplicationContext对象
     */
    public void init(WebApplicationContext wac){
        webApplicationContext = wac;
        AnnotationConfigBeanFactory beanFactory = (AnnotationConfigBeanFactory)wac.getBeanFactory();

        List<BeanDefinition> definitions = beanFactory.getBeanDefinitions();

        if (definitions == null) return;

        for (BeanDefinition beanDefinition : definitions){
            Class targetClass = beanDefinition.getTargetClass();
            if (!BeanUtils.containAnnotation(targetClass, Controller.class)){
                continue;
            }
            RequestMapping typeRM = (RequestMapping)targetClass.getAnnotation(RequestMapping.class);
            String tpyePath = typeRM.value();

            Method[] methods = targetClass.getMethods();
            for (Method method : methods) {
                RequestMapping methodRM = method.getAnnotation(RequestMapping.class);
                if (methodRM == null) continue;
                String methodPath = methodRM.value();

                HandlerMethod handlerMethod = new HandlerMethod();
                handlerMethod.setTargetMethodName(method.getName());
                handlerMethod.setParams(method);
                handlerMethod.setTargetClass(targetClass);
                handlerMethod.setRequestMethod(typeRM.method());
                urlHanderMap.put(StrUtil.catPath(tpyePath, methodPath), handlerMethod);
            }
        }

    }

    public HandlerMethod getHandler(String url) {
        return urlHanderMap.get(url);
    }

    /**
     * <p>获取HTTP get 请求方式中的参数</p>
     * @param handlerMethod
     * @param req
     * @return
     */
    private Object[] resolveRequetParameters(HandlerMethod handlerMethod, HttpServletRequest req) throws IOException {
        List<String> methodParamNames = handlerMethod.getMethodParamsField();
        Object[] paramValue = new Object[methodParamNames.size()];
        int idx = 0;
        for (MethodParameter methodParameter : handlerMethod.getParams()){
            Object obj = resolveCommonRequestParameters(req, methodParameter);
            if (obj == null){
                obj = rsolveRequestBodyParameters(req, methodParameter);
            }
            paramValue[idx] = obj;
            idx++;
        }
        return paramValue;
    }

    /** 解析url中参数*/
    private Object resolveCommonRequestParameters(HttpServletRequest req, MethodParameter methodParameter) {
        Object value = null;
        String pv = req.getParameter(methodParameter.getFieldName());
        if (pv == null){
            return value;
        }
        if (methodParameter.getType() == int.class || methodParameter.getType() == Integer.class ){
            value = Integer.valueOf(pv);
        } else if(methodParameter.getType() == double.class || methodParameter.getType() == Double.class){
            value = Double.valueOf(pv);
        }else {
            value = pv;
        }
        return value;
    }

    /**
     * 1. HandlerAdaptor.invokeHandlerMethod()
     * 2. HandlerMethod.invoke()
     *  2.1. HandlerMethod.getMethodArguagementsValues()
     *      2.1.1 args[] = arguagementResolver.resolveArguagement()
     *          MethodParameters[] methodParameter = HandlerMethod.getMethodParameters()
     *          ArguagementResolverComposite.getArguagementResolver(methodParameter)
     *              concreteArauagementResolve = getConcreteArguagementResolve(methodParameter)
     *              concreteArauagementResolve.resolverArguagement(request,methodParameter,bindFactory)
     *                  methodParameterName
     *                  targetInstance
     *                  binder = BindFactory.getBinder(methodParameterName,target)
     *                  binder.bindRequestParameterValues(request,methodParameter)
     *                     ServletRequestParameterPropertyValues mpvs = new ServletRequestParameterPropertyValues(request);
     *                     binder.doBind(mpvs)
     *                          GenericConvertService.convert()
     *                          GenericConverter = GenericConvertService.getConverter(targetType, sourceType)
     *  2.2. doInvoke(args)
     *
     * @param req
     * @param methodParameter
     * @return
     * @throws IOException
     */
    private Object rsolveRequestBodyParameters(HttpServletRequest req, MethodParameter methodParameter) throws IOException {
        Object value = null;
        String body = StrUtil.convertToStringFromInputStream(HttpServletRequestParser.getBody(req));

        logger.info("-------{}",body);

        return value;
    }
}
