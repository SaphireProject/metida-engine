package metida.engine;

import metida.engine.interfaces.IUserStrategy;
import org.joor.Reflect;
import org.springframework.stereotype.Component;

@Component
public class StrategyCreate {

    public void strCreate() {
        IUserStrategy userStrategy = Reflect.compile(
                "com.example.CompileTest",
                "package com.example;\n" +
                        "import metida.engine.factories.TankFactory; \n" +
                        "import metida.engine.TankFactoryProvider; \n" +
                        "class CompileTest implements metida.engine.interfaces.IUserStrategy {" +
                        "   public void execute() {" +
                        "       TankFactoryProvider.getTankFactory().getTank();" +
                        "  }\n" +
                        "}\n").create().get();

        userStrategy.execute();
    }


}