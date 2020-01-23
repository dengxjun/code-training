package trains.input.command;

import trains.control.process.ExactlyStopQuestionProcess;

/**
 * @author: 邓小军
 * @since: 2019/12/30 17:32
 *
 */
public class RouteCommandForQ7 extends AbstractRouteCommand{

    public RouteCommandForQ7(String content) {
        this.process = ExactlyStopQuestionProcess.getInstance();
        this.content = content;
    }
}
