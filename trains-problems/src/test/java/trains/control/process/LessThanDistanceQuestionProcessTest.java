package trains.control.process;

import org.junit.Assert;
import org.junit.Test;
import trains.BaseTest;
import trains.output.FootPrint;

import static org.junit.Assert.*;

/**
 * @author: 邓小军
 * @since: 2020/1/2 10:24
 *
 */
public class LessThanDistanceQuestionProcessTest extends BaseTest{

    @Test
    public void processQuestion() {
        FootPrint footPrint = LessThanDistanceQuestionProcess.getInstance().processQuestion("CC");
        Assert.assertEquals(7,footPrint.getResult());
    }
}