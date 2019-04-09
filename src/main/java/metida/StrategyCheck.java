package metida;

import metida.interfacable.IUserStrategy;
import org.joor.Reflect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StrategyCheck {

    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyCheck.class);

    /* public void execute() {
        TankFactoryProvider.getTankFactory().get();
    }
    public void init() {
        TankFactoryProvider.getTankFactory().get();
    }

    [
        {
            "id": "user1",
            "strategyPaths": [
                "C://strategy/Strategy1.java"
            ]
        }
    ]
*/


    public boolean check(String strategy) {
        strategy = "package com.example;\n" +
                "    import metida.factory.TankFactory;\n" +
                "    import metida.providers.TankFactoryProvider;\n" +
                "    class CompileTest implements metida.interfacable.IUserStrategy { " +
                strategy +
                "}";
        try {
            IUserStrategy userStrategy;
            userStrategy = Reflect.compile(
                    "com.example.CompileTest",
                    strategy).create().get();

            userStrategy.execute();
            userStrategy.init();

            return true;
        } catch (Exception e) {
            return false;
        }

    }
}