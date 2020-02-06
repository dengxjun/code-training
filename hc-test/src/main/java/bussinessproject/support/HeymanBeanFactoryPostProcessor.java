package bussinessproject.support;

import ioc.annotation.Component;
import ioc.expand.BeanFactoryPostProcessor;
import ioc.factory.BeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/4
 */
@Component
public class HeymanBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static Logger logger = LoggerFactory.getLogger(HeymanBeanFactoryPostProcessor.class);

    @Override
    public void postProcessAfterBeanFactory(BeanFactory beanFactory) {
        logger.debug("-----beanFactory---");
    }
}
