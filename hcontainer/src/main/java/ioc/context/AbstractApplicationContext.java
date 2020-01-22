package ioc.context;

import ioc.factory.BeanFactory;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/20 14:42
 * @Description:
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    private String applicationNam;

    private BeanFactory beanFactory;


    public AbstractApplicationContext(String applicationNam) {
        this.applicationNam = applicationNam;
    }

    public AbstractApplicationContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public AbstractApplicationContext(String applicationNam, BeanFactory beanFactory) {
        this.applicationNam = applicationNam;
        this.beanFactory = beanFactory;
    }

    public abstract void refresh();

    @Override
    public String getApplicationName() {
        return null;
    }


    @Override
    public void loadBeanDefinitions(String scanPackages) {

    }

    @Override
    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(Class cls) {
        return getBeanFactory().getBean(cls);
    }

    @Override
    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void setApplicationNam(String applicationNam) {
        this.applicationNam = applicationNam;
    }
}
