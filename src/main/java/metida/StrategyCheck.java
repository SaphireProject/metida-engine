package metida;

import metida.interfacable.IUserStrategy;
import org.joor.Reflect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StrategyCheck {

    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyCheck.class);

    public boolean check(String strategy) {
        strategy = "package com.example;\n" +
                "import metida.factory.TankFactory;\n" +
                "import metida.object.Tank;\n" +
                "import metida.interfacable.Direction;\n" +
                "import metida.providers.TankFactoryProvider;\n" +
                "class CompileTest implements metida.interfacable.IUserStrategy { \n" +
                strategy +
                "\n}";
        try {
            LOGGER.info(strategy);
            IUserStrategy userStrategy;
            userStrategy = Reflect.compile(
                    "com.example.CompileTest",
                    strategy).create().get();

            userStrategy.init();

            userStrategy.execute();

            LOGGER.info("true");
            return true;
        } catch (Exception e) {
            LOGGER.info("false " + e);
            return false;
        }

    }
}