package bussinessproject.test.support;

import ioc.annotation.Component;
import ioc.expand.BeanFactoryPostProcessor;
import ioc.factory.BeanFactory;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/4
 */
@Component
public class HeymanBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessAfterBeanFactory(BeanFactory beanFactory) {
        System.out.println("-----beanFactory---");
    }
}
