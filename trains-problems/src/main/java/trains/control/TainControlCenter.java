package trains.control;

import trains.core.StationNetWork;
import trains.input.command.RouteCommand;
import trains.output.FootPrint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Auther: 邓小军
 * @Date: 2019/12/30 16:18
 * @Description: 列车调度中心
 */
public class TainControlCenter {

    private String stationInfoStr;

    public TainControlCenter(String stationInfoStr) {
        this.stationInfoStr = stationInfoStr;
    }

    public TainControlCenter inputCheck(){
        if (!Pattern.matches("([A-Z]{2}\\d,)+[A-Z]{2}\\d", stationInfoStr)){
            throw new RuntimeException("输入格式错误：请输入 类似： AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7格式");
        }
        return this;
    }

    public TainControlCenter initStationNetWork(){
        StationNetWork.init(Arrays.asList(stationInfoStr.split(",")));
        return this;
    }

    public List<FootPrint> startProcess(List<RouteCommand> commands){
        if (StationNetWork.getStationMap().size() == 0){
            throw new RuntimeException("请先初始化列车站关系网后再执行调度！");
        }

        List<FootPrint> resultList = new ArrayList<FootPrint>(10);

        commands.forEach((item->{
            resultList.add(item.execute());
        }));

        return resultList;
    }
}
