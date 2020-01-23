package trains.input.command;

import trains.control.process.QuestuionProcess;
import trains.output.FootPrint;

/**
 * @author: 邓小军
 * @since: 2019/12/30 17:27
 *
 */
public abstract class AbstractRouteCommand implements RouteCommand{

    QuestuionProcess process;

    String content;

    @Override
    public FootPrint execute(){
        return process.processQuestion(content);
    }

}
