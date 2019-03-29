package metida;

import metida.interfacable.IUserStrategy;
import org.joor.Reflect;
import org.springframework.stereotype.Component;

@Component
public class StrategyCreate {

    public void strCreate() {
        IUserStrategy userStrategy;
        userStrategy = Reflect.compile(
                "com.example.CompileTest",
                "package com.example;\n" +
                        "import metida.factory.TankFactory; \n" +
                        "import metida.providers.TankFactoryProvider; \n" +
                        "class CompileTest implements metida.interfacable.IUserStrategy {" +
                        "   public void init() {" +
                        "       TankFactoryProvider.getTankFactory().getTank();" +
                        "  }\n" +
                        "}\n").create().get();

        userStrategy.init();
    }
}