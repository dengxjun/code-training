package trains.input.command;

import trains.control.process.DistanceQuestionProcess;

/**
 * @Auther: 邓小军
 * @Date: 2019/12/30 17:32
 * @Description:
 */
public class RouteCommandForQ3 extends AbstractRouteCommand{

    public RouteCommandForQ3(String content) {
        this.process = DistanceQuestionProcess.getInstance();
        this.content = content;
    }
}
