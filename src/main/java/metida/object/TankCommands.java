package metida.object;

public class TankCommands implements Command {

    protected PlayerTank tank;


    public TankCommands(PlayerTank tank) {
        this.tank=tank;
    }

    @Override
    public void execute() {

    }
}
