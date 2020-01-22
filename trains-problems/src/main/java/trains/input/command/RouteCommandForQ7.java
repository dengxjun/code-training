package trains.input.command;

import trains.control.process.ExactlyStopQuestionProcess;

/**
 * @Auther: 邓小军
 * @Date: 2019/12/30 17:32
 * @Description:
 */
public class RouteCommandForQ7 extends AbstractRouteCommand{

    public RouteCommandForQ7(String content) {
        this.process = ExactlyStopQuestionProcess.getInstance();
        this.content = content;
    }
}
