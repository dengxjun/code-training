package web;

import ioc.annotation.Controller;
import ioc.annotation.RequestMapping;
import ioc.factory.AnnotationConfigBeanFactory;
import ioc.factory.BeanDefinition;
import ioc.utils.StrUtil;

import javax.servlet.http.HttpServletRequest;
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

    private Map<String, MethodHander> urlHanderMap = new HashMap<>();

    private WebApplicationContext webApplicationContext;

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
                urlHanderMap.put(StrUtil.catPath(tpyePath, methodPath), methodHander);
            }
        }

    }

    public MethodHander getHandler(String url) {
        return urlHanderMap.get(url);
    }

    public Object invoke(MethodHander methodHander, HttpServletRequest req) {
        Object obj = webApplicationContext.getBean(methodHander.getTargetClass());
        Object[] params = getRequetParameters(methodHander, req);
        return methodHander.invoke(obj,params);
    }

    private Object[] getRequetParameters(MethodHander methodHander, HttpServletRequest req) {
        List<String> methodParamNames = methodHander.getMethodParamsField();
        Object[] paramValue = new Object[methodParamNames.size()];
        int idx = 0;
        for (ParamHandler paramHandler : methodHander.getParams()){
            String pv = req.getParameter(paramHandler.getFieldName());
            if (paramHandler.getType() == int.class || paramHandler.getType() == Integer.class ){
                paramValue[idx] = Integer.valueOf(pv);
            } else if(paramHandler.getType() == double.class || paramHandler.getType() == Double.class){
                paramValue[idx] = Double.valueOf(pv);
            }else {
                paramValue[idx] = pv;
            }
            idx++;
        }
        return paramValue;
    }
}
