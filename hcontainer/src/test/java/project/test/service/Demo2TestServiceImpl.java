package project.test.service;

import project.test.dao.Demo2TestDao;
import ioc.annotation.Component;
import ioc.annotation.Inject;

/**
 * @author: 邓小军
 * @since: 2020/1/19 14:15
 *
 */

@Component("demo2Service")
public class Demo2TestServiceImpl implements Demo2TestService {

    @Inject
    private Demo2TestDao demo2TestDao;

    @Override
    public void method1(){
        System.out.println("run demo2 method 1....");
        demo2TestDao.save();
    }


    @Override
    public void method2(){
        System.out.println("run demo2  method 2....");
        demo2TestDao.save();
    }

    public void setDemo2TestDao(Demo2TestDao demo1Dao) {
        this.demo2TestDao = demo1Dao;
    }
}
