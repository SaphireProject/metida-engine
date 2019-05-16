package metida.factory;


import metida.object.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class TankFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(TankFactory.class);

    private static Game game;

    public Map<Integer, Tank> objectsTank = new HashMap<>();

    public Map<Integer, Tank> getObjectsTank() {
        return objectsTank;
    }

    public void setObjectsTank(Map<Integer, Tank> objectsTank) {
        this.objectsTank = objectsTank;
    }

    //когда создать игру
    @PostConstruct
    public void init() {
        String path="E:/project/metida/test.json";
        game = Game.Initialize(path);
        //game = Game.Initialize(path);
    }

    Random random=new Random();

    public Tank getTank(int idTeam) {
        Tank tank = new Tank(idTeam);
        LOGGER.info("Танк создан " + tank.getId());
        try {
            int x=random.nextInt(game.gameOptions.getWidth());
            int y=random.nextInt(game.gameOptions.getHeight());
            Point point = new Point(x, y);
            if(game.gameOptions.hashmap.get(point.hashCode()) == null){
                //getTank(idTeam);
                game.addObject(tank, random.nextInt(game.gameOptions.getWidth()),
                        random.nextInt(game.gameOptions.getHeight()),
                        game.gameOptions);
                LOGGER.info("не ошибочка");
                //можно добавлять в отдельный список танки, а потом по нему пройтись и получить очереди методов
                objectsTank.put(point.hashCode(),tank);
            }
            else{
                getTank(idTeam);
            }
        } catch (Exception e) {
            LOGGER.info("ошибочка");
        }
        return tank;
    }

    public GoldTank getGoldTank(int idTeam) {
        LOGGER.info("Голд танк создан");
        return new GoldTank(idTeam);
    }

}
