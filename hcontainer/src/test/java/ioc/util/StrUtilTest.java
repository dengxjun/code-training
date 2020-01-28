package ioc.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

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

    @Test
    public void mapRequestBodyString(){
        Map<String, List> stringListMap = StrUtil.mapRequestBodyString("aa=AA&bb=BB&dd=fe&dd=aaa");
        Assert.assertEquals("AA",stringListMap.get("aa").get(0));
        Assert.assertEquals("BB",stringListMap.get("bb").get(0));
        Assert.assertEquals("fe",stringListMap.get("dd").get(0));
        Assert.assertEquals("aaa",stringListMap.get("dd").get(1));
    }
}