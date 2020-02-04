package web;

import ioc.util.CollectionUtils;
import web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public class HandlerExecuteChain {

    private Object handler;

    private List<HandlerInterceptor> interceptors = new ArrayList<>();

    private HandlerExecuteChain(Object handler){
        this.handler = handler;
    }

    public boolean applyPreExecuteHandle(HttpServletRequest request, HttpServletResponse response){
        if (CollectionUtils.isNotEmpty(interceptors)){
            for (HandlerInterceptor interceptor : interceptors){
                if (!interceptor.preExecuteHandler(request, response, this.handler)){
                    return false;
                }
            }
        }
        return true;
    }

    public void applyPostExecuteHandle(HttpServletRequest request, HttpServletResponse response){
        if (CollectionUtils.isNotEmpty(interceptors)){
            for (HandlerInterceptor interceptor : interceptors){
                interceptor.postExecuteHandler(request, response, this.handler);
            }
        }
    }

    public void addInterceptor(HandlerInterceptor handlerInterceptor){
        interceptors.add(handlerInterceptor);
    }

    public static HandlerExecuteChain build(HttpServletRequest req, HandlerMapping mapping) {
        HandlerMethod handlerMethod = mapping.getHandler(getPath(req));
        HandlerExecuteChain handlerExecuteChain = new HandlerExecuteChain(handlerMethod);
        // // TODO: 2020/1/28 getInterceptor() 
        return handlerExecuteChain;
    }

    public Object getHandler() {
        return handler;
    }

    private static String getPath(HttpServletRequest req){
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        return uri.replaceFirst(contextPath,"").replaceFirst(servletPath,"");
    }
}
