package trains.control.process;

import trains.core.Station;
import trains.core.StationNetWork;
import trains.output.FootPrint;
import trains.utils.StringCycleCheckUtil;

/**
 * @author: 邓小军
 * @since: 2019/12/30 10:13
 *  最短站点路径计算-邻接表深度优先遍历
 */
public class ShortestLengthQuestionProcess implements QuestuionProcess{


    QuestuionProcess questuionProcess = DistanceQuestionProcess.getInstance();

    private static ShortestLengthQuestionProcess lengthQuestionProcess = new ShortestLengthQuestionProcess();

    private ShortestLengthQuestionProcess(){}

    public static ShortestLengthQuestionProcess getInstance(){
        return lengthQuestionProcess;
    }

    @Override
    public FootPrint processQuestion(String inputStr) {

        char[] arr = inputStr.toCharArray();

        FootPrint footPrint = new FootPrint(Integer.MAX_VALUE);

        StringBuilder stringBuilder = new StringBuilder();

        calWalkWays(arr[0]+"", arr[1]+"", footPrint, stringBuilder);

        return footPrint;
    }



    private void calWalkWays(String start, String end, FootPrint footPrint, StringBuilder stringBuilder){
        Station station = StationNetWork.getStationMap().get(start);

        stringBuilder.append(station.getName());


        if(stringBuilder.length() > 1 && station.getName().equals(end)){

            int currentWeight = questuionProcess.processQuestion(stringBuilder.toString()).getResult();
            if (currentWeight < footPrint.getResult()){
                footPrint.setResult(currentWeight);
            }


            footPrint.addPrints(stringBuilder.toString());

            station.getNextStations().forEach((item->{
                calWalkWays(item.getToStationName(), end, footPrint, stringBuilder);
                stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());

            }));
            return;

        }else{

            if (footPrint.getResult() != Integer.MAX_VALUE && questuionProcess.processQuestion(stringBuilder.toString()).getResult() > footPrint.getResult()){
                return;
            }

            if (StringCycleCheckUtil.isCycle(stringBuilder.toString())){
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
