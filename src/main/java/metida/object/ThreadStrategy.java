package metida.object;

import metida.controllers.UserController;
import metida.interfacable.IUserStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadStrategy extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadStrategy.class);
    IUserStrategy iUserStrategy;
    public static Integer id = 1;

    public ThreadStrategy(IUserStrategy iUserStrategy) {
        LOGGER.info("создан поток " + id);
        this.iUserStrategy = iUserStrategy;
        this.iUserStrategy.init();
        id++;
    }

    @Override
    public void run() {
        iUserStrategy.execute();
    }

    public void threadWait() {
        try {
            LOGGER.info("жду");
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void threadNotify() {
        LOGGER.info("пошёл");
        notify();
    }

}