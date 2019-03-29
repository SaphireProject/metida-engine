package metida.providers;

import metida.factory.TankFactory;

public class TankFactoryProvider {
    public static TankFactory getTankFactory() {
        TankFactory ctx = (TankFactory) ApplicationContextProvider.getApplicationContext().getBean("tankFactory");
        return ctx;
    }
}
