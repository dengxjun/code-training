package bussinessproject.support;

import ioc.annotation.Component;
import ioc.expand.InitializingBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/5
 */
@Component
public class HeymanInitializingBean implements InitializingBean{
    private static Logger logger = LoggerFactory.getLogger(HeymanInitializingBean.class);

    @Override
    public void afterPropertiesSet() {
        logger.debug("invoke afterPropertiesSet....");
    }
}
