package trains.input.command;

import trains.control.process.MaximumStopQuestionProcess;

/**
 * @author: 邓小军
 * @since: 2019/12/30 17:32
 *
 */
public class RouteCommandForQ6 extends AbstractRouteCommand{

    public RouteCommandForQ6(String content) {
        this.process = MaximumStopQuestionProcess.getInstance();
        this.content = content;
    }
}
