package trains.input.command;

import trains.control.process.MaximumStopQuestionProcess;

/**
 * @Auther: 邓小军
 * @Date: 2019/12/30 17:32
 * @Description:
 */
public class RouteCommandForQ6 extends AbstractRouteCommand{

    public RouteCommandForQ6(String content) {
        this.process = MaximumStopQuestionProcess.getInstance();
        this.content = content;
    }
}
