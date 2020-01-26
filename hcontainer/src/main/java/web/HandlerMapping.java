package web;

import ioc.annotation.Controller;
import ioc.annotation.RequestMapping;
import ioc.factory.AnnotationConfigBeanFactory;
import ioc.factory.BeanDefinition;
import ioc.utils.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.parser.HttpServletRequestParser;

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

    private Map<String, MethodHander> urlHanderMap = new HashMap<>();

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

        List<BeanDefinition> definitions = beanFactory.getBeanDefinitions(Controller.class);

        if (definitions == null) return;

        for (BeanDefinition beanDefinition : definitions){
            Class targetClass = beanDefinition.getTargetClass();
            RequestMapping typeRM = (RequestMapping)targetClass.getAnnotation(RequestMapping.class);
            String tpyePath = typeRM.value();

            Method[] methods = targetClass.getMethods();
            for (Method method : methods) {
                RequestMapping methodRM = method.getAnnotation(RequestMapping.class);
                if (methodRM == null) continue;
                String methodPath = methodRM.value();

                MethodHander methodHander = new MethodHander();
                methodHander.setTargetMethodName(method.getName());
                methodHander.setParams(method);
                methodHander.setTargetClass(targetClass);
                methodHander.setRequestMethod(typeRM.method());
                urlHanderMap.put(StrUtil.catPath(tpyePath, methodPath), methodHander);
            }
        }

    }

    public MethodHander getHandler(String url) {
        return urlHanderMap.get(url);
    }

    /**
     * <p>Handler调用逻辑<br>
     *  <li> 1. 从hc容器中获取Controller bean
     *  <li> 2. 解析request请求中的参数
     *  <li> 3. 执行调用
     * </p>
     * @param methodHander
     * @param req
     * @return ModelAndView对象
     */
    public Object invoke(MethodHander methodHander, HttpServletRequest req) throws IOException {
        Object obj = webApplicationContext.getBean(methodHander.getTargetClass());
        Object[] params = resolveRequetParameters(methodHander, req);
        return methodHander.invoke(obj,params);
    }

    /**
     * <p>获取HTTP get 请求方式中的参数</p>
     * @param methodHander
     * @param req
     * @return
     */
    private Object[] resolveRequetParameters(MethodHander methodHander, HttpServletRequest req) throws IOException {
        List<String> methodParamNames = methodHander.getMethodParamsField();
        Object[] paramValue = new Object[methodParamNames.size()];
        int idx = 0;
        for (ParamHandler paramHandler : methodHander.getParams()){
            Object obj = resolveCommonRequestParameters(req, paramHandler);
            if (obj == null){
                obj = rsolveRequestBodyParameters(req, paramHandler);
            }
            paramValue[idx] = obj;
            idx++;
        }
        return paramValue;
    }

    /** 解析url中参数*/
    private Object resolveCommonRequestParameters(HttpServletRequest req, ParamHandler paramHandler) {
        Object value = null;
        String pv = req.getParameter(paramHandler.getFieldName());
        if (pv == null){
            return value;
        }
        if (paramHandler.getType() == int.class || paramHandler.getType() == Integer.class ){
            value = Integer.valueOf(pv);
        } else if(paramHandler.getType() == double.class || paramHandler.getType() == Double.class){
            value = Double.valueOf(pv);
        }else {
            value = pv;
        }
        return value;
    }

    private Object rsolveRequestBodyParameters(HttpServletRequest req, ParamHandler paramHandler) throws IOException {
        Object value = null;
        String body = StrUtil.convertToStringFromInputStream(HttpServletRequestParser.getBody(req));

        logger.info("-------{}",body);

        return value;
    }
}
