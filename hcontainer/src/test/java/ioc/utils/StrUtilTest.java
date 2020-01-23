package ioc.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: 邓小军
 * @since: 2020/1/21 11:00
 *
 */
public class StrUtilTest {

    @Test
    public void toLowCaseFirstChar() {
    }

    @Test
    public void toUpperCaseFirstChar() {
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void isNotEmpty() {
    }

    @Test
    public void getSetterMethodName() {
    }

    @Test
    public void catPath() {
        Assert.assertEquals("/aa/bb/cc", StrUtil.catPath("aa","bb","cc"));
        Assert.assertEquals("/aa/bb/cc", StrUtil.catPath("/aa","bb/","cc"));
    }
}