package metida.factory;


import metida.object.Bullet;
import metida.object.Game;
import metida.object.GoldTank;
import metida.object.Tank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TankFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(TankFactory.class);

    private static TankFactory instance;
    private static Game game;
    private static int x = 12, y = 12;

    public static TankFactory Initialize() {
        if (instance == null) {
            instance = new TankFactory();

            String path = "C:/Users/vdi/IdeaProjects/error/metida-engine/test.json";
            game = Game.Initialize(path);
        }
        return instance;
    }

    public Tank getTank(int idTeam) {
        LOGGER.info("Танк создан " + idTeam);

        Tank tank = new Tank(idTeam);
        game.addObject(tank, x, y, game.gameOptions);
        x = -2;
        y = -2;
        return tank;
    }

    public GoldTank getGoldTank(int idTeam) {
        LOGGER.info("Голд танк создан");
        return new GoldTank(idTeam);
    }

}
