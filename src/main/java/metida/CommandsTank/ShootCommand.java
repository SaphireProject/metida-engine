package metida.CommandsTank;

import metida.interfacable.Direction;
import metida.object.Tank;

public class ShootCommand extends TankCommands {

    private Direction direction;
    private TypeCommands type;

    public ShootCommand(Tank tank, Direction direction) {
        super(tank);
        this.direction = direction;
        this.type=TypeCommands.SHOOT;
    }

    @Override
    public void execute(){
        tank.shootExecute(direction);
    }
}
