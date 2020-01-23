package ioc.context;

import ioc.factory.BeanFactory;

/**
 * @author: 邓小军
 * @since: 2020/1/20 14:40
 *
 */
public interface ApplicationContext extends BeanFactory{
    String getApplicationName();

    BeanFactory getBeanFactory();
}
