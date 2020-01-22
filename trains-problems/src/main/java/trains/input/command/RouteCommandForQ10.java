package trains.input.command;

import trains.control.process.LessThanDistanceQuestionProcess;

/**
 * @Auther: 邓小军
 * @Date: 2019/12/30 17:32
 * @Description:
 */
public class RouteCommandForQ10 extends AbstractRouteCommand{

    public RouteCommandForQ10(String content) {
        this.process = LessThanDistanceQuestionProcess.getInstance();
        this.content = content;
    }
}
