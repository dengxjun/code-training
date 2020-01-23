package trains.input.builder;

import trains.input.command.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 邓小军
 * @since: 2019/12/31 11:39
 *  默认命令构造器
 */
public class DefaultCommandBuilder implements CommandBuilder {
    @Override
    public List<RouteCommand> buildCommander() {

        List<RouteCommand> commands = new ArrayList<>();

        RouteCommand commandForQ1 = new RouteCommandForQ1("ABC");
        commands.add(commandForQ1);

        RouteCommand commandForQ2 = new RouteCommandForQ2("AD");
        commands.add(commandForQ2);

        RouteCommand commandForQ3 = new RouteCommandForQ3("ADC");
        commands.add(commandForQ3);

        RouteCommand commandForQ4 = new RouteCommandForQ4("AEBCD");
        commands.add(commandForQ4);

        RouteCommand commandForQ5 = new RouteCommandForQ5("AED");
        commands.add(commandForQ5);

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
