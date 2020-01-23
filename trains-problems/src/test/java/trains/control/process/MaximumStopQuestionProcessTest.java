package trains.control.process;

import org.junit.Assert;
import org.junit.Test;
import trains.BaseTest;
import trains.output.FootPrint;

import static org.junit.Assert.*;

/**
 * @author: 邓小军
 * @since: 2020/1/2 10:26
 *
 */
public class MaximumStopQuestionProcessTest extends BaseTest{

    @Test
    public void processQuestion() {
        FootPrint footPrint = MaximumStopQuestionProcess.getInstance().processQuestion("CC");
        Assert.assertEquals(2,footPrint.getResult());
    }
}