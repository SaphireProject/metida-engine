package metida.object;

import metida.interfacable.Direction;

public class ShootCommand extends TankCommands {
    private Direction direction;

    public ShootCommand(Tank tank, Direction direction) {
        super(tank);
        this.direction = direction;
    }

    @Override
    public void execute(){
        tank.shoot(direction);
    }
}
