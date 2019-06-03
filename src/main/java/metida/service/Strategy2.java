package metida.service;

import metida.interfacable.Direction;
import metida.interfacable.IUserStrategy;
import metida.object.Tank;
import metida.providers.TankFactoryProvider;

public class Strategy2 implements IUserStrategy {
    Tank tank;
    //Tank tank1;
    public void execute(){
        for(int i=0;i<5;i++){
            tank.shoot(Direction.RIGHT);
            tank.moveRight();
            tank.moveDown();
            tank.moveLeft();
            tank.moveUp();
        }
        /*for(int i=0;i<5;i++){
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
        for(int i=0;i<5;i++){
            tank.moveRight();
            tank.moveRight();
            tank.moveDown();
            tank.shoot(Direction.UP);
            tank.shoot(Direction.RIGHT);
        }
        /*for(int i=0;i<5;i++){
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
       // tank1.moveRight();
    }
    public void init(){
        tank= TankFactoryProvider.getTankFactory().getTank(2);
        //tank1= TankFactoryProvider.getTankFactory().getTank(2);
    }
}
