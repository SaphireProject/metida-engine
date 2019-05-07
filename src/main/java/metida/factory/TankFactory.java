package metida.factory;


import metida.object.Game;
import metida.object.GoldTank;
import metida.object.Tank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
public class TankFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(TankFactory.class);

    private static Game game;
/*
    //когда создать игру
    @PostConstruct
    public void init() {
        //String path = "E:/idea/error/metida-engine/test.json";
        game = Game.Initialize("E:/project/metida/test.json");
    }
*/
    Random random=new Random();

    public Tank getTank(int idTeam) {

        //ToDo:рандомные координаты, но требуется проверка что не наложатся друг на друга
        Tank tank = new Tank(idTeam);
        LOGGER.info("Танк создан " + idTeam);
       /* try {
            game.addObject(tank, random.nextInt(game.gameOptions.getWidth()),
                    random.nextInt(game.gameOptions.getHeight()),
                    game.gameOptions);
            //можно добавлять в отдельный список танки, а потом по нему пройтись и получить очереди методов
        } catch (Exception e) {
            LOGGER.info("ошибочка");
        }*/
        return tank;
    }

    public GoldTank getGoldTank(int idTeam) {
        LOGGER.info("Голд танк создан");
        return new GoldTank(idTeam);
    }

}
