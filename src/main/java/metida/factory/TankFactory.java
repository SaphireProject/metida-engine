package metida.factory;


import metida.object.Game;
import metida.object.GoldTank;
import metida.object.Tank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TankFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(TankFactory.class);

    private static Game game;
    private static int x = 12, y = 12;

    @PostConstruct
    public void init() {

        String path = "E:/idea/error/metida-engine/test.json";
        game = Game.Initialize(path);
    }

    public Tank getTank(int idTeam) {
        LOGGER.info("Танк создан " + idTeam);

        Tank tank = new Tank(idTeam);
        try {
            game.addObject(tank, x, y, game.gameOptions);
        } catch (Exception e) {
            LOGGER.info(String.valueOf(e));
        }
        return tank;
    }

    public GoldTank getGoldTank(int idTeam) {
        LOGGER.info("Голд танк создан");
        return new GoldTank(idTeam);
    }

}
