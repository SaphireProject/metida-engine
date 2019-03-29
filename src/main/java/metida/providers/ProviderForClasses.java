package metida.providers;

import metida.factory.TankFactory;
import metida.factory.WallFactory;

public class ProviderForClasses {

    public static TankFactory getTankFactory() {
        TankFactory ctx = (TankFactory) ApplicationContextProvider.getApplicationContext().getBean("tankFactory");
        return ctx;
    }

    public static WallFactory getWallFactory() {
        WallFactory ctx = (WallFactory) ApplicationContextProvider.getApplicationContext().getBean("wallFactory");
        return ctx;
    }

}