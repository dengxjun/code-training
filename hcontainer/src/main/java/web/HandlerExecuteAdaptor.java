package web;

import web.method.HandlerMethod;
import web.method.ServletInvocableHandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public class HandlerExecuteAdaptor {

    /**
     * <p>调用映射到的方法 </p>
     * 1. 从request中获取参数值 <br/>
     * 2. 执行方法调用
     * @param req
     * @param resp
     * @param handlerMethod
     * @return
     */
    public Object invokeHandleMethod(HttpServletRequest req, HttpServletResponse resp, HandlerMethod handlerMethod) throws Exception{
        ServletInvocableHandlerMethod invocableHandlerMethod = new ServletInvocableHandlerMethod(handlerMethod);
        Object[] args = invocableHandlerMethod.resolveMethodParametersValue(req, handlerMethod);

        Object resultValue = invocableHandlerMethod.invoke(getHandleTargetObj(handlerMethod, req), args);
        return resultValue;
    }

    /**
     * <p>获取调用对象Controller<br>
     *  <li> 1. 从hc容器中获取Controller bean
     *  <li> 2. 解析request请求中的参数
     * </p>
     * @param handlerMethod
     * @param req
     * @return ModelAndView对象
     */
    private Object getHandleTargetObj(HandlerMethod handlerMethod, HttpServletRequest req){
        WebApplicationContext wac = (WebApplicationContext)req.getServletContext().getAttribute(WebApplicationContext.WebApplicationContext);
        Object obj = wac.getBean(handlerMethod.getTargetClass());
        if (obj == null){
            throw new RuntimeException("can not found bean instance of class: "+ handlerMethod.getTargetClass());
        }
        return obj;
    }
}
