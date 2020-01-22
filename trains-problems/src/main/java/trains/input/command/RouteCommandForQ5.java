package trains.input.command;

import trains.control.process.DistanceQuestionProcess;

/**
 * @Auther: 邓小军
 * @Date: 2019/12/30 17:32
 * @Description:
 */
public class RouteCommandForQ5 extends AbstractRouteCommand{

    public RouteCommandForQ5(String content) {
        this.process = DistanceQuestionProcess.getInstance();
        this.content = content;
    }
}
