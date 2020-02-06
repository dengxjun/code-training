package bussinessproject.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/5
 */
public class HeymanInvocationHandler implements InvocationHandler {
    private static Logger logger = LoggerFactory.getLogger(HeymanInvocationHandler.class);

    private Object target;
    private HeymanAspect heymanAspect;

    public HeymanInvocationHandler(Object target, HeymanAspect heymanAspect) {
        this.target = target;
        this.heymanAspect = heymanAspect;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        heymanAspect.before();
        Object obj = method.invoke(target, args);
        logger.debug("--------"+method.getName());
        heymanAspect.after();
        return obj;
    }
}
