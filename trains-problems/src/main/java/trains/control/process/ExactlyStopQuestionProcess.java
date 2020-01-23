package trains.control.process;


import trains.core.Station;
import trains.core.StationNetWork;
import trains.output.FootPrint;

/**
 * @author: 邓小军
 * @since: 2019/12/30 10:12
 *  固定站点计算-邻接表深度优先遍历
 */
public class ExactlyStopQuestionProcess implements QuestuionProcess{

    private static ExactlyStopQuestionProcess questionProcess = new ExactlyStopQuestionProcess();

    private ExactlyStopQuestionProcess(){}

    public static ExactlyStopQuestionProcess getInstance(){
        return questionProcess;
    }

    @Override
    public FootPrint processQuestion(String inputStr) {
        char[] arr = inputStr.toCharArray();

        FootPrint footPrint = new FootPrint();
        // 用来记录行走轨迹
        StringBuilder stringBuilder = new StringBuilder();

        calWalkWays(arr[0]+"", arr[1]+"", footPrint, stringBuilder);
        return footPrint;
    }


    private void calWalkWays(String start, String end, FootPrint footPrint, StringBuilder stringBuilder){

        Station station = StationNetWork.getStationMap().get(start);

        stringBuilder.append(station.getName());


        if(station.getName().equals(end)){

            if (stringBuilder.length() != 5){
                station.getNextStations().forEach((item->{
                    calWalkWays(item.getToStationName(), end, footPrint, stringBuilder);
                    stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());

                }));
                return;
            }
            footPrint.addPrints(stringBuilder.toString());
            footPrint.resultAdd();
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
