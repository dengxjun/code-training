package ioc.expand;

import ioc.factory.BeanFactory;

/**
 * <p>实现该接口的bean class 在bean factory初始化完成后将会调用, 用于对beanFactory做一些修改</p>
 *
 * @author heyman
 * @date 2020/2/4
 */
public interface BeanFactoryPostProcessor {

    void postProcessAfterBeanFactory(BeanFactory beanFactory);
}
