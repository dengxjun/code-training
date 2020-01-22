package trains.control.process;

import trains.core.RouteInfo;
import trains.core.Station;
import trains.core.StationNetWork;
import trains.output.FootPrint;

/**
 * @Auther: 邓小军
 * @Date: 2019/12/30 10:11
 * @Description: 特定路长计算
 */
public class DistanceQuestionProcess implements QuestuionProcess {

    private static DistanceQuestionProcess questionProcess = new DistanceQuestionProcess();

    private DistanceQuestionProcess(){}

    public static DistanceQuestionProcess getInstance(){
        return questionProcess;
    }

    @Override
    public FootPrint processQuestion(String inputStr) {
        int result = process(inputStr);
        return new FootPrint(result);
    }

    private int process(String inputStr){
        char[] reqStrArr = inputStr.toCharArray();

        int length = 0;

        for (int i = 0; i < reqStrArr.length-1; i++) {

            boolean isFound = false;
            String start = reqStrArr[i]+"";
            String end = reqStrArr[i+1]+"";
            Station station = StationNetWork.getStationMap().get(start);

            if (station == null) continue;

            for (RouteInfo routeInfo : station.getNextStations()){
                if (routeInfo.getToStationName().equals(end)){
                    length += routeInfo.getDistance();
                    isFound = true;
                    break;
                }
            }

            if (!isFound){
                length = -1;
            }
        }


        return length;
    }
}
