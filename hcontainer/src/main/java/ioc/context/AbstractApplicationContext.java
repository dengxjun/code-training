package ioc.context;

import ioc.expand.ApplicationContextAware;
import ioc.expand.BeanFactoryAware;
import ioc.expand.BeanFactoryPostProcessor;
import ioc.factory.AbstractBeanFactory;
import ioc.factory.BeanDefinition;
import ioc.factory.BeanFactory;
import ioc.util.CollectionUtils;

import java.util.List;

/**
 *
 * @author: 邓小军
 * @since: 2020/1/20 14:42
 *
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

    /**
     * 1. 遍历BeanDefinitions,筛选实现BeanFactoryPostProcessor接口的BeanDefinition
     * 2. 获取bean, 并调用postProcessAfterBeanFactory方法
     */
    protected void applyBeanFactoryProcessor() {
        List<BeanDefinition> beanDefinitions = ((AbstractBeanFactory)getBeanFactory()).getBeanDefinitionAssignableFrom(BeanFactoryPostProcessor.class);
        if (CollectionUtils.isEmpty(beanDefinitions)){
            return;
        }
        for (BeanDefinition beanDefinition : beanDefinitions){
            BeanFactoryPostProcessor postProcessor = (BeanFactoryPostProcessor) getBeanFactory().getBean(beanDefinition.getBeanName());
            postProcessor.postProcessAfterBeanFactory(getBeanFactory());
        }
    }

    protected void applyBeanFactoryAware() {
        List<BeanDefinition> beanDefinitions = ((AbstractBeanFactory)getBeanFactory()).getBeanDefinitionAssignableFrom(BeanFactoryAware.class);
        if (CollectionUtils.isEmpty(beanDefinitions)){
            return;
        }
        for (BeanDefinition beanDefinition : beanDefinitions){
            BeanFactoryAware factoryAware = (BeanFactoryAware) getBeanFactory().getBean(beanDefinition.getBeanName());
            factoryAware.setBeanFactoryAware(getBeanFactory());
        }
    }

    protected void applyApplicationContextAware() {
        List<BeanDefinition> beanDefinitions = ((AbstractBeanFactory)getBeanFactory()).getBeanDefinitionAssignableFrom(ApplicationContextAware.class);
        if (CollectionUtils.isEmpty(beanDefinitions)){
            return;
        }
        for (BeanDefinition beanDefinition : beanDefinitions){
            ApplicationContextAware applicationContextAware = (ApplicationContextAware) getBeanFactory().getBean(beanDefinition.getBeanName());
            applicationContextAware.setApplicationContextAware(this);
        }
    }


    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void setApplicationNam(String applicationNam) {
        this.applicationNam = applicationNam;
    }
}
