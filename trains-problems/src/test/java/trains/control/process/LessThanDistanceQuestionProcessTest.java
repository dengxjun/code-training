package trains.control.process;

import org.junit.Assert;
import org.junit.Test;
import trains.BaseTest;
import trains.output.FootPrint;

import static org.junit.Assert.*;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/2 10:24
 * @Description:
 */
public class LessThanDistanceQuestionProcessTest extends BaseTest{

    @Test
    public void processQuestion() {
        FootPrint footPrint = LessThanDistanceQuestionProcess.getInstance().processQuestion("CC");
        Assert.assertEquals(7,footPrint.getResult());
    }
}