package ioc.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.test.service.Demo2TestService;
import project.test.service.DemoTestService;
import ioc.factory.AnnotationConfigBeanFactory;
import ioc.factory.AnnotationConfigBeanFactoryTest;
import org.junit.Test;

/**
 * @author: 邓小军
 * @since: 2020/1/20 15:11
 *
 */
public class AnnotationApplicationContextTest {
    private static Logger log = LoggerFactory.getLogger(AnnotationConfigBeanFactoryTest.class);

    @Test
    public void test() {
        log.debug("test start {}",33);
        AnnotationApplicationContext applicationContext = new AnnotationApplicationContext("bussinessproject");
        applicationContext.refresh();
        AnnotationConfigBeanFactory beanFactory = (AnnotationConfigBeanFactory)applicationContext.getBeanFactory();
        try {
            DemoTestService obj1 = (DemoTestService)beanFactory.getBean(DemoTestService.class);

            Demo2TestService obj2 = (Demo2TestService)beanFactory.getBean("demo2Service");

            obj1.method1();
            obj2.method1();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}