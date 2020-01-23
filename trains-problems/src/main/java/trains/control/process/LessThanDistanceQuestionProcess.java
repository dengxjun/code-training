package trains.control.process;

import trains.core.Station;
import trains.core.StationNetWork;
import trains.output.FootPrint;

/**
 * @author: 邓小军
 * @since: 2019/12/30 10:14
 *  最少站点计算-邻接表深度优先遍历
 */
public class LessThanDistanceQuestionProcess implements QuestuionProcess{

    QuestuionProcess questuionProcess = DistanceQuestionProcess.getInstance();

    private static LessThanDistanceQuestionProcess questionProcess = new LessThanDistanceQuestionProcess();

    private LessThanDistanceQuestionProcess(){}

    public static LessThanDistanceQuestionProcess getInstance(){
        return questionProcess;
    }

    @Override
    public FootPrint processQuestion(String inputStr) {
        char[] arr = inputStr.toCharArray();

        FootPrint footPrint = new FootPrint();

        StringBuilder stringBuilder = new StringBuilder();

        calWalkWays(arr[0]+"", arr[1]+"", footPrint, stringBuilder);

        return footPrint;
    }

    private void calWalkWays(String start, String end, FootPrint footPrint, StringBuilder stringBuilder){
        Station station = StationNetWork.getStationMap().get(start);

        stringBuilder.append(station.getName());

        if(station.getName().equals(end)){

            int currentWeight = questuionProcess.processQuestion(stringBuilder.toString()).getResult();
            if (currentWeight > 1 && currentWeight < 30){
                footPrint.addPrints(stringBuilder.toString());
                footPrint.resultAdd();
            }

            if (stringBuilder.length() < 30){
                station.getNextStations().forEach((item->{
                    calWalkWays(item.getToStationName(), end, footPrint, stringBuilder);
                    stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());

                }));
                return;
            }
            return;
        }else{

            if (questuionProcess.processQuestion((stringBuilder.toString())).getResult() > 29){
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
