package metida;


import metida.factory.TankFactory;
import metida.factory.WallFactory;
import metida.interfacable.Direction;
import metida.object.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.Collections;

public class App {

    private static Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws IOException {

        String path="E:/project/metida/test.json";
        Game game = Game.Initialize(path);

        TankFactory factory = new TankFactory();
        //WallFactory wallFactory = new WallFactory();

        Tank tank1=factory.getTank(1);
        //Tank tank2=factory.getTank(2);
        //Tank tank3=factory.getGoldTank(2);
        //Tank tank4=factory.getTank(4);


        //перенести логику addobject в gettank
        game.addObject(tank1,12,12, game.gameOptions);
        //game.addObject(tank2,10,10, game.gameOptions);
        //game.addObject(tank3,6,6, game.gameOptions);
       // game.addObject(tank4,5,10, game.gameOptions);


/*
        //tank2.moveLeft();
        tank4.moveDown();
        tank4.moveDown();
        tank2.turn(Direction.LEFT);
        tank1.turn(Direction.DOWN);
        tank4.turn(Direction.RIGHT);

        tank2.shoot(Direction.LEFT);

        tank1.shoot(Direction.DOWN);
        tank4.shoot(Direction.RIGHT);


        //tank2.turn(Direction.DOWN);

        //tank1.moveLeft();
        game.action();
        game.action();*/
    }
}
