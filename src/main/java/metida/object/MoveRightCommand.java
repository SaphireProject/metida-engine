package metida.object;

public class MoveRightCommand extends TankCommands{

    private TypeCommands type;

    public MoveRightCommand(Tank tank) {
        super(tank);
        this.type=TypeCommands.MOVERIGHT;
    }

    @Override
    public void execute(){
        tank.moveRightExecute();
    }
}
