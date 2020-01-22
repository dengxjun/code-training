package trains.input.builder;

import trains.input.command.RouteCommand;

import java.util.List;

/**
 * @Auther: 邓小军
 * @Date: 2019/12/31 11:35
 * @Description: 指挥命令构建接口
 */
public interface CommandBuilder {

    List<RouteCommand> buildCommander();
}
