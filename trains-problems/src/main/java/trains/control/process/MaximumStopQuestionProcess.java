package trains.control.process;

import trains.core.Station;
import trains.core.StationNetWork;
import trains.output.FootPrint;

/**
 * @Auther: 邓小军
 * @Date: 2019/12/30 10:12
 * @Description: 最多站点计算-邻接表深度优先遍历
 */
public class MaximumStopQuestionProcess implements QuestuionProcess{

    private static MaximumStopQuestionProcess stopQuestionProcess = new MaximumStopQuestionProcess();

    private MaximumStopQuestionProcess(){}


    public static MaximumStopQuestionProcess getInstance(){
        return stopQuestionProcess;
    }

    @Override
    public FootPrint processQuestion(String inputStr) {
        char[] arr = inputStr.toCharArray();

        FootPrint footPrint = new FootPrint();

        StringBuilder stringBuilder = new StringBuilder();

        calWalkWays(arr[0]+"", arr[1]+"", footPrint, stringBuilder);

        return footPrint;
    }

    private void calWalkWays(String start, String end, final FootPrint footPrint, StringBuilder stringBuilder){

        Station station = StationNetWork.getStationMap().get(start);

        stringBuilder.append(station.getName());

        if(station.getName().equals(end)){

            if (stringBuilder.length() > 1 && stringBuilder.length() <= 4){
                footPrint.addPrints(stringBuilder.toString());
                footPrint.resultAdd();
            }

            if (stringBuilder.length() < 4){
                station.getNextStations().forEach((item->{
                    calWalkWays(item.getToStationName(), end, footPrint, stringBuilder);
                    stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());

                }));
                return;
            }
            return;
        }else{

            if (stringBuilder.length() == 5){
                return;
            }

            station.getNextStations().forEach((item->{
                calWalkWays(item.getToStationName(), end, footPrint, stringBuilder);
                stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
                return;
            }));


        }
    }
}
