package bussinessproject.service;

import bussinessproject.dao.Demo2Dao;
import ioc.annotation.Component;
import ioc.annotation.Inject;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/19 14:15
 * @Description:
 */

@Component("demo2Service")
public class Demo2ServiceImpl implements Demo2Service {

    @Inject
    private Demo2Dao demo2Dao;

    @Override
    public void method1(){
        System.out.println("run demo2 method 1....");
        demo2Dao.save();
    }


    @Override
    public void method2(){
        System.out.println("run demo2  method 2....");
        demo2Dao.save();
    }

    public void setDemo2Dao(Demo2Dao demo1Dao) {
        this.demo2Dao = demo1Dao;
    }
}
