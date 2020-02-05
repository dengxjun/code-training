package bussinessproject.test.support;

import ioc.annotation.Component;
import ioc.annotation.Inject;
import ioc.expand.BeanPostProcessor;

import java.lang.reflect.Proxy;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/2/5
 */
@Component
public class HeymanBeanPostProcessor implements BeanPostProcessor {

    @Inject
    private HeymanBeanFactoryAware heymanBeanFactoryAware;

    @Override
    public Object postProcessorBeforeInitializtion(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessorAfterInitializtion(Object bean, String beanName) {
        System.out.println("invoke postProcessorAfterInitializtion");
        if (HeymanAspect.needIntercept(bean)){
            HeymanAspect heymanAspect = (HeymanAspect) heymanBeanFactoryAware.getBeanFactory().getBean(HeymanAspect.class);
            if(heymanAspect != null){
                bean = Proxy.newProxyInstance(this.getClass().getClassLoader(), bean.getClass().getInterfaces(), new HeymanInvocationHandler(bean));
            }
        }
        return bean;
    }

}
