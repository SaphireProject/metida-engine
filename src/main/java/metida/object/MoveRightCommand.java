package metida.object;

public class MoveRightCommand extends TankCommands{

    public MoveRightCommand(Tank tank) {
        super(tank);
    }

    @Override
    public void execute(){
        tank.moveRight();
    }
}
