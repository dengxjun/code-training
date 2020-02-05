package bussinessproject.test.support;

import ioc.annotation.Component;
import ioc.expand.InitializingBean;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/5
 */
@Component
public class HeymanInitializingBean implements InitializingBean{

    @Override
    public void afterPropertiesSet() {
        System.out.println("invoke afterPropertiesSet....");
    }
}
