package bussinessproject.service;

import bussinessproject.dao.Demo1Dao;
import ioc.annotation.Inject;
import ioc.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: 邓小军
 * @since: 2020/1/19 14:15
 *
 */

@Service
public class DemoServiceImpl implements DemoService {
    private static Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Inject
    private Demo1Dao demo1Dao;

    @Override
    public void method1(){
        logger.debug("run method 1....");
        demo1Dao.save();
    }


    @Override
    public void method2(){
        logger.debug("run method 2....");
    }

    public void setDemo1Dao(Demo1Dao demo1Dao) {
        this.demo1Dao = demo1Dao;
        demo1Dao.save();
    }
}
