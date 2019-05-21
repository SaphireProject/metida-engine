package metida.factory;

import metida.CommandsTank.*;
import metida.interfacable.Direction;
import metida.object.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(CommandFactory.class);

    public MoveDownCommand getMoveDownCommand(Tank tank){
        LOGGER.info("Создание команды MoveDownCommand с параметрами "+tank.toString());
        return new MoveDownCommand(tank);
    }

    public MoveLeftCommand getMoveLeftCommand(Tank tank){
        LOGGER.info("Создание команды MoveLeftCommand с параметрами "+tank.toString());
        return new MoveLeftCommand(tank);
    }

    public MoveUpCommand getMoveUpCommand(Tank tank){
        LOGGER.info("Создание команды MoveUpCommand с параметрами "+tank.toString());
        return new MoveUpCommand(tank);
    }

    public MoveRightCommand getMoveRightCommand(Tank tank){
        LOGGER.info("Создание команды MoveRightCommand с параметрами "+tank.toString());
        return new MoveRightCommand(tank);
    }

    public TurnCommand getTurnCommand(Tank tank, Direction direction){
        LOGGER.info("Создание команды TurnCommand с параметрами "+tank.toString()+' '+direction);
        return new TurnCommand(tank, direction);
    }

    public ShootCommand getShootCommand(Tank tank, Direction direction){
        LOGGER.info("Создание команды ShootCommand с параметрами "+tank.toString()+' '+direction);
        return new ShootCommand(tank, direction);
    }
}
