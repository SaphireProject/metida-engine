package metida.service;

import metida.interfacable.IUserStrategy;
import metida.object.Tank;
import metida.providers.TankFactoryProvider;
import metida.factory.TankFactory;

public class Strategy1 implements IUserStrategy {
    Tank tank;
    public void execute(){
        for(int i=0;i<4;i++){
            tank.moveUp();
            tank.moveRight();
            tank.moveDown();
        }
        tank.moveUp();
        tank.moveRight();
    }
    public void init(){
        tank= TankFactoryProvider.getTankFactory().getTank(1);
    }
}
