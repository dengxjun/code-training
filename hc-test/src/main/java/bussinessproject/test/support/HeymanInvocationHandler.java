package bussinessproject.test.support;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/5
 */
public class HeymanInvocationHandler implements InvocationHandler {

    private Object target;

    public HeymanInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }
}
