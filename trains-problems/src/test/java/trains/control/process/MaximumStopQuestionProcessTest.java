package trains.control.process;

import org.junit.Assert;
import org.junit.Test;
import trains.BaseTest;
import trains.output.FootPrint;

import static org.junit.Assert.*;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/2 10:26
 * @Description:
 */
public class MaximumStopQuestionProcessTest extends BaseTest{

    @Test
    public void processQuestion() {
        FootPrint footPrint = MaximumStopQuestionProcess.getInstance().processQuestion("CC");
        Assert.assertEquals(2,footPrint.getResult());
    }
}