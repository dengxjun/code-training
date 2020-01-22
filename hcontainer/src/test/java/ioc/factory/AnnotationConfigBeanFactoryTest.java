package ioc.factory;

import bussinessproject.service.Demo2Service;
import bussinessproject.service.DemoService;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/19 14:17
 * @Description:
 */
public class AnnotationConfigBeanFactoryTest {
    private static Logger log = Logger.getLogger(AnnotationConfigBeanFactoryTest.class);

    @Test
    public void loadBeanDefinitions() {
        log.debug("start.....");
        BeanFactory beanFactory = new AnnotationConfigBeanFactory();
        beanFactory.loadBeanDefinitions("bussinessproject");
        try {
            DemoService obj1 = (DemoService)beanFactory.getBean(DemoService.class);

            Demo2Service obj2 = (Demo2Service)beanFactory.getBean("demo2Service");

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