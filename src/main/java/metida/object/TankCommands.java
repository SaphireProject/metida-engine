package metida.object;

public class TankCommands implements Command {

    protected Tank tank;


    public TankCommands(Tank tank) {
        this.tank=tank;
    }

    @Override
    public void execute() {

    }
}
