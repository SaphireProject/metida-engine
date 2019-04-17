package metida.object;

public class MoveUpCommand extends TankCommands {

    public MoveUpCommand(Tank tank) {
        super(tank);
    }

    @Override
    public void execute(){
        tank.moveUp();
    }
}
