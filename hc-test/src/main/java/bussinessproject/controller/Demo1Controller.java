package bussinessproject.controller;

import bussinessproject.entity.User;
import bussinessproject.service.Demo2Service;
import bussinessproject.service.DemoService;
import ioc.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: 邓小军
 * @since: 2020/1/20 14:53
 *
 */

@Controller
@RequestMapping("demo1")
public class Demo1Controller {
    private static Logger logger = LoggerFactory.getLogger(Demo1Controller.class);

    @Inject
    private DemoService demoService;

    @Inject
    private Demo2Service demo2Service;

    @RequestMapping("test")
    public String getTest(String name, Integer age){
        logger.debug("Demo1Controller getTest","aa");
        demoService.method1();
        return name+"#"+age;
    }

    @RequestMapping("test2")
    public String getTest2(String name, int age){

        return name+"#"+age;
    }

    @RequestMapping("test3")
    public String getTest3(){

        return "hello";
    }

    @RequestMapping("test4")
    public void getTest4(){

    }

    @RequestMapping("test5")
    public String getTest5(String name, double age){

        return name+"#"+age;
    }

    @RequestMapping("test6")
    public String getTest5(@RequestBody User user){

        return user.getName()+"#"+user.getAge();
    }

    public void setDemo2Service(Demo2Service demo2Service) {
        this.demo2Service = demo2Service;
    }

    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }
}
