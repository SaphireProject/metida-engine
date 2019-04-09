package metida;


import metida.factory.TankFactory;
import metida.factory.WallFactory;
import metida.interfacable.Direction;
import metida.object.Game;
import metida.object.Point;
import metida.object.Tank;
import metida.object.Wall;
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

        TankFactory factory=new TankFactory();
        WallFactory wallFactory=new WallFactory();

       // Tank tank1=factory.getTank(1);
        Tank tank2=factory.getTank(2);
        //Tank tank3=factory.getGoldTank(2);
        Tank tank4=factory.getTank(4);

       // game.addObject(tank1,12,12, game.gameOptions);
        game.addObject(tank2,10,10, game.gameOptions);
        //game.addObject(tank3,6,6, game.gameOptions);
        game.addObject(tank4,5,10, game.gameOptions);

        Point point = new Point(10,10);
        LOGGER.info("hash original"+point.hashCode());
        Point point1 = new Point(9,10);
        LOGGER.info("hash new" + point1.hashCode());

        //tank2.moveLeft();
        tank2.turn(Direction.LEFT);
        LOGGER.info("Танк повернулся");
        tank2.shoot(Direction.LEFT);
        LOGGER.info("Танк выстрелил влево");

        //tank2.turn(Direction.DOWN);

        //tank1.moveLeft();
        game.action();






       // LOGGER.info("Координаты танка2 "+tank2.getX()+tank2.getY());
        LOGGER.info("Объект с координатами 12:12 " + game.findObject(12,12));
        LOGGER.info("Объект с координатами 11:12 " + game.findObject(11,12));
        LOGGER.info("Объект с координатами 9:10 "  + game.findObject(9,10));
        LOGGER.info("Объект с координатами 10:10 " + game.findObject(10,10));

/*
        System.out.println(tank4.getX());
        System.out.println(tank4.getY());
        System.out.println(game.findObject(9,10));
        Point point=new Point(9,10);
        System.out.println(point.hashCode());
        System.out.println(game.gameOptions.hashmap.get(point.hashCode()));
        System.out.println(Collections.singletonList(game.gameOptions.hashmap));
        //System.out.println(tank3.getDistance(10,10));
*/
    }
}
