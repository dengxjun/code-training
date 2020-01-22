package trains.control.process;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import trains.BaseTest;
import trains.control.TainControlCenter;
import trains.output.FootPrint;

import static org.junit.Assert.*;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/2 09:50
 * @Description:
 */
public class DistanceQuestionProcessTest extends BaseTest {

    @Test
    public void processQuestion() {
        FootPrint footPrint = DistanceQuestionProcess.getInstance().processQuestion("ABC");
        Assert.assertEquals(9,footPrint.getResult());

        FootPrint footPrint1 = DistanceQuestionProcess.getInstance().processQuestion("AD");
        Assert.assertEquals(5,footPrint1.getResult());

        FootPrint footPrint2 = DistanceQuestionProcess.getInstance().processQuestion("ADC");
        Assert.assertEquals(13,footPrint2.getResult());

        FootPrint footPrint3 = DistanceQuestionProcess.getInstance().processQuestion("AEBCD");
        Assert.assertEquals(22,footPrint3.getResult());
    }
}