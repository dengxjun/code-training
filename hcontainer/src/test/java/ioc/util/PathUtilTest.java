package ioc.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: 邓小军
 * @since: 2020/1/19 17:40
 *
 */
public class PathUtilTest {

    @Test
    public void convertToFullClasspath() {
        Assert.assertEquals("bussinessproject.service.DemoServiceImpl",PathUtil.convertToFullClasspath("bussinessproject/service/DemoServiceImpl.class"));
    }
}