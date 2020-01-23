package trains.input.builder;

import trains.input.command.RouteCommand;
import trains.input.command.RouteCommandForQ10;
import trains.input.command.RouteCommandForQ8;
import trains.input.command.RouteCommandForQ9;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 邓小军
 * @since: 2019/12/31 11:48
 *  扩展命令构造器3
 */
public class ExtCommandBuilder3 implements CommandBuilder {
    @Override
    public List<RouteCommand> buildCommander() {

        List<RouteCommand> commands = new ArrayList<>();

        RouteCommand commandForQ8 = new RouteCommandForQ8("AC");
        commands.add(commandForQ8);

        RouteCommand commandForQ9 = new RouteCommandForQ9("BB");
        commands.add(commandForQ9);

        RouteCommand commandForQ10 = new RouteCommandForQ10("CC");
        commands.add(commandForQ10);

        return commands;
    }
}
