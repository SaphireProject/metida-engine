package metida;

import metida.interfacable.IUserStrategy;
import org.joor.Reflect;
import org.springframework.stereotype.Component;

@Component
public class StrategyCreate {

    public void strCreate(String strategy) {
        IUserStrategy userStrategy;
        userStrategy = Reflect.compile(
                "com.example.CompileTest",
                "package com.example;\n" +
                        "import metida.factory.TankFactory; \n" +
                        "import metida.providers.TankFactoryProvider; \n" +
                        "class CompileTest implements metida.interfacable.IUserStrategy {" +
                        "   public void init() {" +
                        "  }\n" +
                        "}\n").create().get();

        /*IUserStrategy userStrategy;
        userStrategy = Reflect.compile(
                "com.example.CompileTest",
                strategy).create().get();

        userStrategy.init();*/

    }
}