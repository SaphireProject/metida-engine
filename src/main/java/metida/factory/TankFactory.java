package metida.factory;


import metida.object.Bullet;
import metida.object.GoldTank;
import metida.object.Tank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TankFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(TankFactory.class);

    private static TankFactory instance;

    public static TankFactory Initialize() {
        if (instance == null)
            instance = new TankFactory();
        return instance;
    }

    public Tank getTank(int idTeam) {
        LOGGER.info("Танк создан");
        return new Tank(idTeam);
    }

    public GoldTank getGoldTank(int idTeam) {
        LOGGER.info("Голд танк создан");
        return new GoldTank(idTeam);
    }

}
