package ioc.factory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.test.service.Demo2TestService;
import project.test.service.DemoTestService;

/**
 * @author: 邓小军
 * @since: 2020/1/19 14:17
 *
 */
public class AnnotationConfigBeanFactoryTest {
    private static Logger log = LoggerFactory.getLogger(AnnotationConfigBeanFactoryTest.class);

    @Test
    public void loadBeanDefinitions() {
        log.debug("start.....{}",1);
        AnnotationConfigBeanFactory beanFactory = new AnnotationConfigBeanFactory();
        beanFactory.loadBeanDefinitions("project");
        try {
            DemoTestService obj1 = (DemoTestService)beanFactory.getBean(DemoTestService.class);

            Demo2TestService obj2 = (Demo2TestService)beanFactory.getBean("demo2Service");

            obj1.method1();
            obj2.method1();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getBean() {
    }
}