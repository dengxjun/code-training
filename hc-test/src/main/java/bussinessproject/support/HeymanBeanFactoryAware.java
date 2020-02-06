package bussinessproject.support;

import ioc.annotation.Component;
import ioc.expand.BeanFactoryAware;
import ioc.factory.BeanFactory;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/4
 */
@Component
public class HeymanBeanFactoryAware implements BeanFactoryAware {
    private BeanFactory beanFactory;
    @Override
    public void setBeanFactoryAware(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
