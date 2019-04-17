package metida.object;

public class MoveLeftCommand extends TankCommands {
    public MoveLeftCommand(Tank tank) {
        super(tank);
    }

    @Override
    public void execute(){
        tank.moveLeft();
    }
}
