package trains.input.command;

import trains.control.process.LessThanDistanceQuestionProcess;

/**
 * @author: 邓小军
 * @since: 2019/12/30 17:32
 *
 */
public class RouteCommandForQ10 extends AbstractRouteCommand{

    public RouteCommandForQ10(String content) {
        this.process = LessThanDistanceQuestionProcess.getInstance();
        this.content = content;
    }
}
