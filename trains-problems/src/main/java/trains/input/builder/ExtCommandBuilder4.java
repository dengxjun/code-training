package trains.input.builder;

import trains.input.command.RouteCommand;
import trains.input.command.RouteCommandForQ10;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 邓小军
 * @Date: 2019/12/31 11:49
 * @Description: 扩展命令构造器4
 */
public class ExtCommandBuilder4 implements CommandBuilder {
    @Override
    public List<RouteCommand> buildCommander() {

        List<RouteCommand> commands = new ArrayList<>();

        RouteCommand commandForQ10 = new RouteCommandForQ10("CC");
        commands.add(commandForQ10);

        return commands;
    }
}
