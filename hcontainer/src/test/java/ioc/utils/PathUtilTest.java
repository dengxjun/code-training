package ioc.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/19 17:40
 * @Description:
 */
public class PathUtilTest {

    @Test
    public void convertToFullClasspath() {
        Assert.assertEquals("bussinessproject.service.DemoServiceImpl",PathUtil.convertToFullClasspath("bussinessproject/service/DemoServiceImpl.class"));
    }
}