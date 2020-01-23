package ioc.context;

import bussinessproject.service.Demo2Service;
import bussinessproject.service.DemoService;
import ioc.factory.AnnotationConfigBeanFactory;
import ioc.factory.AnnotationConfigBeanFactoryTest;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author: 邓小军
 * @since: 2020/1/20 15:11
 *
 */
public class AnnotationApplicationContextTest {
    private static Logger log = Logger.getLogger(AnnotationConfigBeanFactoryTest.class);

    @Test
    public void test() {
        AnnotationApplicationContext applicationContext = new AnnotationApplicationContext("bussinessproject");
        applicationContext.refresh();
        AnnotationConfigBeanFactory beanFactory = (AnnotationConfigBeanFactory)applicationContext.getBeanFactory();
        try {
            DemoService obj1 = (DemoService)beanFactory.getBean(DemoService.class);

            Demo2Service obj2 = (Demo2Service)beanFactory.getBean("demo2Service");

            obj1.method1();
            obj2.method1();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}