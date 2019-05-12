package metida.object;

public class MoveLeftCommand extends TankCommands {

    private TypeCommands type;

    public MoveLeftCommand(Tank tank) {
        super(tank);
        this.type=TypeCommands.MOVELEFT;
    }

    @Override
    public void execute(){
        tank.moveLeftExecute();
    }
}
