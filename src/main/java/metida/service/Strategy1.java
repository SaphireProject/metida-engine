package metida.service;

import metida.interfacable.IUserStrategy;
import metida.object.Tank;
import metida.providers.TankFactoryProvider;
import metida.factory.TankFactory;

public class Strategy1 implements IUserStrategy {
    Tank tank;
    public void execute(){
        tank.moveUp();
        tank.moveRight();
    }
    public void init(){
        tank= TankFactoryProvider.getTankFactory().getTank(1);
    }
}
