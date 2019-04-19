package metida.object;

public class MoveLeftCommand extends TankCommands {

    private TypeCommands type;

    public MoveLeftCommand(PlayerTank tank) {
        super(tank);
        this.type=TypeCommands.MOVELEFT;
    }

    @Override
    public void execute(){
        tank.moveLeft();
    }
}
