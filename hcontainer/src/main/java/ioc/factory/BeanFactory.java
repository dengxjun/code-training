package ioc.factory;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/19 14:05
 * @Description:
 */
public interface BeanFactory {

    void loadBeanDefinitions(String scanPackages);

    Object getBean(String beanName);

    Object getBean(Class cls);
}
