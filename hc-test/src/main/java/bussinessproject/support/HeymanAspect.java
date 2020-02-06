package bussinessproject.support;

import ioc.annotation.Component;
import ioc.factory.AnnotationConfigBeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/5
 */
@Component("heymanAspect")
public class HeymanAspect {
    private static Logger log = LoggerFactory.getLogger(HeymanAspect.class);

    public void pointCut(){

    }

    public void before(){
        log.debug("-----------HeymanAspect before method be called------------");
    }

    public void after(){
        log.debug("-----------HeymanAspect after method be called------------");
    }

    public static boolean needIntercept(Object bean){
        if (bean.getClass().getTypeName().equals("bussinessproject.service.DemoServiceImpl")){
            return true;
        }
        return false;
    }

}
