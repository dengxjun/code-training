package trains.control.process;

import org.junit.Assert;
import org.junit.Test;
import trains.BaseTest;
import trains.output.FootPrint;

import static org.junit.Assert.*;

/**
 * @Auther: 邓小军
 * @Date: 2020/1/2 10:21
 * @Description:
 */
public class ExactlyStopQuestionProcessTest extends BaseTest{

    @Test
    public void processQuestion() {
        FootPrint footPrint = ExactlyStopQuestionProcess.getInstance().processQuestion("AC");
        Assert.assertEquals(3,footPrint.getResult());
    }
}