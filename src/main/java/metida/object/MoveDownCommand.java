package metida.object;

public class MoveDownCommand extends TankCommands{
    public MoveDownCommand(Tank tank) {
        super(tank);
    }

    @Override
    public void execute(){
        tank.moveDown();
    }
}
