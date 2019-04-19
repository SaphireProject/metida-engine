package metida.object;

import metida.interfacable.Direction;

public class TurnCommand extends TankCommands {
    private Direction direction;
    private TypeCommands type;

    public TurnCommand(PlayerTank tank,Direction direction) {
        super(tank);
        this.direction = direction;
        this.type=TypeCommands.TURN;
    }

    @Override
    public void execute(){
        tank.turn(direction);
    }
}
