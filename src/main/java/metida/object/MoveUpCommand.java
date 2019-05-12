package metida.object;

public class MoveUpCommand extends TankCommands {

    private TypeCommands type;

    public MoveUpCommand(Tank tank) {
        super(tank);
        this.type=TypeCommands.MOVEUP;
    }

    @Override
    public void execute(){
        tank.moveUpExecute();
    }
}
