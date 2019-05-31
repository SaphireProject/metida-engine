package metida.service;

import metida.interfacable.Direction;
import metida.interfacable.IUserStrategy;
import metida.object.Tank;
import metida.providers.TankFactoryProvider;
import metida.factory.TankFactory;

public class Strategy1 implements IUserStrategy {
    Tank tank;
    public void execute(){
        for(int i=0;i<30;i++){
            tank.shoot(Direction.LEFT);
            tank.moveRight();
            tank.shoot(Direction.UP);
            tank.moveDown();
        }
        tank.moveUp();
        tank.moveRight();
    }
    public void init(){
        tank= TankFactoryProvider.getTankFactory().getTank(1);
    }
}
