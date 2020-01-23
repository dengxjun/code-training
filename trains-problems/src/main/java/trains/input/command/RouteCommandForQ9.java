package trains.input.command;

import trains.control.process.ShortestLengthQuestionProcess;

/**
 * @author: 邓小军
 * @since: 2019/12/30 17:32
 *
 */
public class RouteCommandForQ9 extends AbstractRouteCommand{

    public RouteCommandForQ9(String content) {
        this.process = ShortestLengthQuestionProcess.getInstance();
        this.content = content;
    }
}
