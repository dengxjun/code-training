package trains;

import org.junit.BeforeClass;
import trains.control.TainControlCenter;

/**
 * @author: 邓小军
 * @since: 2020/1/2 09:51
 *
 */
public class BaseTest {

    static TainControlCenter tainControlCenter = null;

    @BeforeClass
    public static void setUp() throws Exception {
        tainControlCenter = new TainControlCenter("AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7")
                .inputCheck()
                .initStationNetWork();
    }
}
