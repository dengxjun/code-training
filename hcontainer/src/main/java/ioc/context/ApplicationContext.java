package ioc.context;

import ioc.factory.BeanFactory;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/20 14:40
 * @Description:
 */
public interface ApplicationContext extends BeanFactory{
    String getApplicationName();

    BeanFactory getBeanFactory();
}
