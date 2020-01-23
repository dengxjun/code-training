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
        beanFactory.loadBeanDefinitions(packageScan);
    }
}
