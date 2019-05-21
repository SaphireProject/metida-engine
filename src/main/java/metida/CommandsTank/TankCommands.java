package metida.CommandsTank;

import metida.CommandsTank.Command;
import metida.object.Tank;

public class TankCommands implements Command {

    protected Tank tank;


    public TankCommands(Tank tank) {
        this.tank=tank;
    }

    @Override
    public void execute() {

    }
}
