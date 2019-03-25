package metida.engine;

import metida.engine.factories.TankFactory;

public class TankFactoryProvider {

    public static TankFactory getTankFactory() {
        TankFactory ctx = (TankFactory) ApplicationContextProvider.getApplicationContext().getBean("tankFactory");
        return ctx;
    }

}