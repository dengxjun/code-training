package trains.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 邓小军
 * @Date: 2019/12/30 14:17
 * @Description: 火车站连接关系
 */
public class StationNetWork {

    private static final Map<String,Station> stationMap = new HashMap<String, Station>();


    public static void init(List<String> inputList){
        inputList.forEach((item->{
            char[] cArr = item.toCharArray();
            Station station = stationMap.get(cArr[0]+"");
            if (station == null){
                station = new Station(cArr[0]+"", new RouteInfo(cArr[1]+"",cArr[2]-'0'));
                stationMap.put(cArr[0]+"", station);
            }else {
                station.addRouteInfo(new RouteInfo(cArr[1]+"",cArr[2]-'0'));
            }
        }));
    }

    /**
     * 返回不可被修改的map
     * @return
     */
    public static Map<String,Station> getStationMap(){
        return Collections.unmodifiableMap(stationMap);
    }

}
