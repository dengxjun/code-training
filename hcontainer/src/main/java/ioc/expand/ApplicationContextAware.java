package ioc.expand;

import ioc.context.ApplicationContext;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/4
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContextAware(ApplicationContext applicationContext);
}
