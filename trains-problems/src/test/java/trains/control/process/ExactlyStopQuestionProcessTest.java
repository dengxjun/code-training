package trains.control.process;

import org.junit.Assert;
import org.junit.Test;
import trains.BaseTest;
import trains.output.FootPrint;

import static org.junit.Assert.*;

/**
 * @author: 邓小军
 * @since: 2020/1/2 10:21
 *
 */
public class ExactlyStopQuestionProcessTest extends BaseTest{

    @Test
    public void processQuestion() {
        FootPrint footPrint = ExactlyStopQuestionProcess.getInstance().processQuestion("AC");
        Assert.assertEquals(3,footPrint.getResult());
    }
}