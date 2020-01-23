package trains.input.command;

import trains.control.process.ShortestLengthQuestionProcess;

/**
 * @author: 邓小军
 * @since: 2019/12/30 17:32
 *
 */
public class RouteCommandForQ8 extends AbstractRouteCommand{

    public RouteCommandForQ8(String content) {
        this.process = ShortestLengthQuestionProcess.getInstance();
        this.content = content;
    }
}
