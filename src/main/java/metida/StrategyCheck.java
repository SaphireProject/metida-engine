package metida;

import metida.interfacable.IUserStrategy;
import org.joor.Reflect;
import org.springframework.stereotype.Component;

@Component
public class StrategyCheck {

    /*package com.example;
        import metida.factory.TankFactory;
        import metida.providers.TankFactoryProvider;
        class CompileTest implements metida.interfacable.IUserStrategy {
        public void execute() {
            TankFactoryProvider.getTankFactory().get();
        }
        public void init() {
            TankFactoryProvider.getTankFactory().get();
        }
        }*/

    public boolean check(String strategy) {

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