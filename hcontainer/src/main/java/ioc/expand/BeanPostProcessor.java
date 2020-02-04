package ioc.expand;

/**
 * <p>实现该接口的bean class 在bean初始化前后将会调用，可以对bean做进一步分装，比如生成代理对象 </p>
 *
 * @author heyman
 * @date 2020/2/4
 */
public interface BeanPostProcessor {

    Object postProcessorBeforeInitializtion(Object bean, String beanName);

    Object postProcessorAfterInitializtion(Object bean, String beanName);
}
