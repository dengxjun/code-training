package bussinessproject.controller;

import ioc.annotation.Controller;
import ioc.annotation.RequestMapping;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/20 14:53
 * @Description:
 */

@Controller
@RequestMapping("demo1")
public class Demo1Controller {

    @RequestMapping("test")
    public String getTest(String name, Integer age){

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
}