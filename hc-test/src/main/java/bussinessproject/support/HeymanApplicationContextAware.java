package bussinessproject.support;

import ioc.annotation.Component;
import ioc.context.ApplicationContext;
import ioc.expand.ApplicationContextAware;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/4
 */
@Component
public class HeymanApplicationContextAware implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContextAware(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
