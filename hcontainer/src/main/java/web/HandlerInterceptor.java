package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public interface HandlerInterceptor {

    boolean preExecuteHandler(HttpServletRequest request, HttpServletResponse response, Object handler);

    void postExecuteHandler(HttpServletRequest request, HttpServletResponse response, Object handler);
}
