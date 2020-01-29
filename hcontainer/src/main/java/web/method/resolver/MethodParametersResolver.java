package web.method.resolver;

import web.method.MethodParameter;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>方法参数值处理器: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public interface MethodParametersResolver {

    /**
     * 根据方法参数判断该解析器是否满足解析要求
     * @param parameter
     * @return true if support, false if not support
     */
    boolean supportParameterType(MethodParameter parameter);

    /**
     * 从request中获取指定参数值
     * @param req
     * @param parameter 方法参数分装
     * @return 参数值
     */
    Object getMethodParametersValue(HttpServletRequest req, MethodParameter parameter) throws Exception;
}
