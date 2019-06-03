package metida.service;

import metida.interfacable.Direction;
import metida.interfacable.IUserStrategy;
import metida.object.Tank;
import metida.providers.TankFactoryProvider;

public class Strategy2 implements IUserStrategy {
    Tank tank;
    //Tank tank1;
    public void execute(){
        for(int i=0;i<2;i++){
            tank.moveDown();
            tank.moveRight();
            for(int j=0;j<2;j++){
                tank.moveRight();
                tank.shoot(Direction.RIGHT);
                tank.moveUp();
                for(int n=0;n<2;n++){
                    tank.moveRight();
                    tank.moveRight();
                    tank.moveDown();
                    tank.shoot(Direction.UP);
                }
                tank.shoot(Direction.UP);
            }
            tank.moveLeft();
            tank.shoot(Direction.UP);
            tank.moveDown();
        }

        for(int i=0;i<2;i++){
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
       // tank1.moveRight();
    }
    public void init(){
        tank= TankFactoryProvider.getTankFactory().getTank(2);
        //tank1= TankFactoryProvider.getTankFactory().getTank(2);
    }
}
