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
        for(int i=0;i<2;i++){
            tank.moveUp();
            tank.moveRight();
            for(int j=0;j<2;j++){
                tank.moveRight();
                tank.shoot(Direction.RIGHT);
                tank.moveUp();
                tank.shoot(Direction.UP);
            }
            tank.moveLeft();
            tank.shoot(Direction.UP);
            tank.moveDown();
            for(int k=0;k<2;k++){
                tank.shoot(Direction.LEFT);
                tank.moveLeft();
                tank.moveUp();
                tank.shoot(Direction.DOWN);
                tank.shoot(Direction.UP);
            }
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
