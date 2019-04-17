package metida.object;

import metida.interfacable.Direction;

public class TurnCommand extends TankCommands {
    private Direction direction;

    public TurnCommand(Tank tank,Direction direction) {
        super(tank);
        this.direction = direction;
    }

    @Override
    public void execute(){
        tank.turn(direction);
    }
}
