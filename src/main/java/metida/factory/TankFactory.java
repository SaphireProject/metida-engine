package metida.factory;


import metida.object.GoldTank;
import metida.object.Tank;
import org.springframework.stereotype.Component;

@Component
public class TankFactory {

    private static TankFactory instance;

    public static TankFactory Initialize() {
        if (instance == null)
            instance = new TankFactory();
        return instance;
    }

    public Tank getTank(int idTeam) {
        return new Tank(idTeam);
    }

    public GoldTank getGoldTank(int idTeam) {
        return new GoldTank(idTeam);
    }

}