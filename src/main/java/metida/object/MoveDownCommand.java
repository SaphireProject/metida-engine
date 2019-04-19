package metida.object;

public class MoveDownCommand extends TankCommands{

    private TypeCommands type;

    public MoveDownCommand(PlayerTank tank) {
        super(tank);
        this.type=TypeCommands.MOVEDOWN;
    }

    @Override
    public void execute(){
        tank.moveDown();
    }
}
