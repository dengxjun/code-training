package ioc.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * <p>Description: </p>
 *
 * @author heyman
 * @date 2020/1/28
 */
public class CollectionUtilsTest {
    @Test
    public void isEmpty() throws Exception {
        List<String> strings = new ArrayList<>();
        List<String> strings2 = new ArrayList<String>(){{add("aa");}};
        Assert.assertEquals(true,CollectionUtils.isEmpty(strings));
        Assert.assertEquals(false,CollectionUtils.isEmpty(strings2));
    }

    @Test
    public void isNotEmpty() throws Exception {
        List<String> strings = new ArrayList<>();
        List<String> strings2 = new ArrayList<String>(){{add("aa");}};
        Assert.assertEquals(false,CollectionUtils.isNotEmpty(strings));
        Assert.assertEquals(true,CollectionUtils.isNotEmpty(strings2));
    }

}