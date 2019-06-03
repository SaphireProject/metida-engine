package metida.service;

import metida.interfacable.Direction;
import metida.interfacable.IUserStrategy;
import metida.object.Tank;
import metida.providers.TankFactoryProvider;
import metida.factory.TankFactory;

public class Strategy1 implements IUserStrategy {
    Tank tank;
    //Tank tank1;
    public void execute(){
        tank.shoot(Direction.LEFT);
        tank.shoot(Direction.DOWN);
        for(int i=0;i<5;i++){
            tank.moveUp();
            tank.moveRight();
            tank.moveDown();
            tank.shoot(Direction.UP);
            tank.moveDown();
        }
        for(int i=0;i<5;i++){
            tank.shoot(Direction.LEFT);
            tank.moveLeft();
            tank.moveUp();
            tank.shoot(Direction.DOWN);
            tank.shoot(Direction.UP);
        }
        for(int i=0;i<5;i++){
            tank.moveRight();
            tank.moveRight();
            tank.moveDown();
            tank.shoot(Direction.UP);
            tank.shoot(Direction.RIGHT);
        }
       /* for(int i=0;i<5;i++){
            tank1.shoot(Direction.LEFT);
            tank1.moveLeft();
            tank1.moveUp();
            tank1.shoot(Direction.DOWN);
            tank1.shoot(Direction.UP);
        }
        for(int i=0;i<5;i++){
            tank1.shoot(Direction.RIGHT);
            tank1.moveRight();
            tank1.moveDown();
            tank1.shoot(Direction.UP);
            tank1.shoot(Direction.DOWN);
        }*/
        for(int i=0;i<5;i++){
            tank.shoot(Direction.LEFT);
            tank.moveLeft();
            tank.moveUp();
            tank.shoot(Direction.DOWN);
            tank.shoot(Direction.UP);
        }

        tank.moveUp();
        //tank1.moveRight();

    }
    public void init(){
        tank= TankFactoryProvider.getTankFactory().getTank(1);
        //tank1= TankFactoryProvider.getTankFactory().getTank(1);
    }
}
