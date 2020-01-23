package trains.input.builder;

import trains.input.command.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 邓小军
 * @since: 2019/12/31 11:45
 *  扩展命令构造器2
 */
public class ExtCommandBuilder2 implements CommandBuilder {
    @Override
    public List<RouteCommand> buildCommander() {

        List<RouteCommand> commands = new ArrayList<>();

        RouteCommand commandForQ6 = new RouteCommandForQ6("CC");
        commands.add(commandForQ6);

        RouteCommand commandForQ7 = new RouteCommandForQ7("AC");
        commands.add(commandForQ7);

        RouteCommand commandForQ8 = new RouteCommandForQ8("AC");
        commands.add(commandForQ8);

        RouteCommand commandForQ9 = new RouteCommandForQ9("BB");
        commands.add(commandForQ9);

        RouteCommand commandForQ10 = new RouteCommandForQ10("CC");
        commands.add(commandForQ10);

        return commands;
    }
}
