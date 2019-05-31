package metida.service;

import metida.interfacable.Direction;
import metida.interfacable.IUserStrategy;
import metida.object.Tank;
import metida.providers.TankFactoryProvider;

public class Strategy2 implements IUserStrategy {
    Tank tank;
    public void execute(){
        for(int i=0;i<30;i++){
            tank.shoot(Direction.RIGHT);
            tank.moveRight();
            tank.moveDown();
            tank.shoot(Direction.UP);
        }
        tank.moveUp();
        tank.moveRight();
    }
    public void init(){
        tank= TankFactoryProvider.getTankFactory().getTank(2);
    }
}
