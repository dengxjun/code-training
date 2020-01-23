package trains.input.routenetwork;

import org.junit.BeforeClass;
import org.junit.Test;
import trains.core.StationNetWork;

import java.util.Arrays;
import java.util.List;

/**
 * @author: 邓小军
 * @since: 2019/12/30 15:08
 *
 */
public class StationNetWorkTest {

    @BeforeClass
    public static void setUp() throws Exception {
        List<String> parms = Arrays.asList("AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7".split(","));
        StationNetWork.init(parms);
    }

    @Test
    public void getStationMap(){
        StationNetWork.getStationMap().forEach((k, v)->{
            System.out.println(k+"::");
            v.getNextStations().forEach((item -> {
                System.out.print(item.getToStationName() + "-" + item.getDistance()+"-----");
            }));

            System.out.println();
        });

    }



}