package trains.input.command;

import trains.control.process.QuestuionProcess;
import trains.output.FootPrint;

/**
 * @Auther: 邓小军
 * @Date: 2019/12/30 17:27
 * @Description:
 */
public abstract class AbstractRouteCommand implements RouteCommand{

    QuestuionProcess process;

    String content;

    @Override
    public FootPrint execute(){
        return process.processQuestion(content);
    }

}
