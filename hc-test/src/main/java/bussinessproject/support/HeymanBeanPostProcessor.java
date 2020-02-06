package bussinessproject.support;

import bussinessproject.service.DemoService;
import bussinessproject.service.DemoServiceImpl;
import ioc.annotation.Component;
import ioc.annotation.Inject;
import ioc.expand.BeanPostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/5
 */
@Component
public class HeymanBeanPostProcessor implements BeanPostProcessor {
    private static Logger logger = LoggerFactory.getLogger(HeymanBeanPostProcessor.class);

    @Inject
    private HeymanBeanFactoryAware heymanBeanFactoryAware;

    @Override
    public Object postProcessorBeforeInitializtion(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessorAfterInitializtion(Object bean, String beanName) {
        logger.debug("invoke postProcessorAfterInitializtion");
        if (HeymanAspect.needIntercept(bean)){
            HeymanAspect heymanAspect = (HeymanAspect) heymanBeanFactoryAware.getBeanFactory().getBean(HeymanAspect.class);
            if(heymanAspect != null){
                bean = Proxy.newProxyInstance(this.getClass().getClassLoader(), bean.getClass().getInterfaces(), new HeymanInvocationHandler((DemoService)bean, heymanAspect));
            }
        }
        return bean;
    }

    public void setHeymanBeanFactoryAware(HeymanBeanFactoryAware heymanBeanFactoryAware) {
        this.heymanBeanFactoryAware = heymanBeanFactoryAware;
    }

    public static void main(String[] args) {
        DemoService demoService = new DemoServiceImpl();
        HeymanAspect heymanAspect = new HeymanAspect();
        Object obj = Proxy.newProxyInstance(HeymanBeanPostProcessor.class.getClassLoader(), demoService.getClass().getInterfaces(), new HeymanInvocationHandler(demoService, heymanAspect));
        ((DemoService)obj).method1();
        System.out.println(obj);
    }
}
