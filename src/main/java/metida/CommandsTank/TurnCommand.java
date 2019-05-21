package metida.CommandsTank;

import metida.interfacable.Direction;
import metida.object.Tank;

public class TurnCommand extends TankCommands {
    private Direction direction;
    private TypeCommands type;

    public TurnCommand(Tank tank, Direction direction) {
        super(tank);
        this.direction = direction;
        this.type=TypeCommands.TURN;
    }

    @Override
    public void execute(){
        tank.turnExecute(direction);
    }
}
