package trains.input.command;

import trains.control.process.ShortestLengthQuestionProcess;

/**
 * @Auther: 邓小军
 * @Date: 2019/12/30 17:32
 * @Description:
 */
public class RouteCommandForQ9 extends AbstractRouteCommand{

    public RouteCommandForQ9(String content) {
        this.process = ShortestLengthQuestionProcess.getInstance();
        this.content = content;
    }
}
