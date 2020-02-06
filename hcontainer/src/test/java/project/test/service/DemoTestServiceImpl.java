package project.test.service;

import ioc.annotation.Service;
import project.test.dao.Demo1TestDao;
import ioc.annotation.Component;
import ioc.annotation.Inject;

/**
 * @author: 邓小军
 * @since: 2020/1/19 14:15
 *
 */

@Service
public class DemoTestServiceImpl implements DemoTestService {

    @Inject
    private Demo1TestDao demo1TestDao;

    @Override
    public void method1(){
        System.out.println("run method 1....");
        demo1TestDao.save();
    }


    @Override
    public void method2(){
        System.out.println("run method 2....");
    }

    public void setDemo1TestDao(Demo1TestDao demo1TestDao) {
        this.demo1TestDao = demo1TestDao;
        demo1TestDao.save();
    }
}
