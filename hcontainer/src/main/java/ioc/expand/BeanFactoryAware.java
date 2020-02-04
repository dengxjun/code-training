package ioc.expand;

import ioc.factory.BeanFactory;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/4
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactoryAware(BeanFactory beanFactory);
}
