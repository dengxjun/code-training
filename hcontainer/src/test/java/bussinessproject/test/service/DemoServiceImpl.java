package bussinessproject.test.service;

import bussinessproject.test.dao.Demo1Dao;
import bussinessproject.test.dao.Demo1Dao;
import ioc.annotation.Component;
import ioc.annotation.Inject;

/**
 * @author: 邓小军
 * @since: 2020/1/19 14:15
 *
 */

@Component()
public class DemoServiceImpl implements DemoService {

    @Inject
    private Demo1Dao demo1Dao;

    @Override
    public void method1(){
        System.out.println("run method 1....");
        demo1Dao.save();
    }


    @Override
    public void method2(){
        System.out.println("run method 2....");
    }

    public void setDemo1Dao(Demo1Dao demo1Dao) {
        this.demo1Dao = demo1Dao;
        demo1Dao.save();
    }
}
