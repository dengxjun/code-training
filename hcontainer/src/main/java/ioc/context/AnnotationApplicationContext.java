package ioc.context;

import ioc.factory.AnnotationConfigBeanFactory;

/**
 * @author: 邓小军
 * @since: 2020/1/20 14:44
 *
 */
public class AnnotationApplicationContext extends AbstractApplicationContext {

    private String packageScan;

    public AnnotationApplicationContext(String packageScan) {
        super(new AnnotationConfigBeanFactory());
        this.packageScan = packageScan;
    }

    @Override
    public void refresh() {
        AnnotationConfigBeanFactory beanFactory = (AnnotationConfigBeanFactory) getBeanFactory();
        //加载bean定义
        beanFactory.loadBeanDefinitions(packageScan);
        // 让Context持有BeanFactory对象
        setBeanFactory(beanFactory);
        // 处理BeanFactoryAware调用
        applyBeanFactoryAware();
        // 处理BeanFactoryProcessor调用
        applyBeanFactoryProcessor();
        // 处理ApplicationContextAware调用
        applyApplicationContextAware();
    }

}
