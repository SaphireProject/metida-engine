package metida;

import metida.object.Tank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener {

    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationEventListener.class);
    @EventListener(ApplicationStartedEvent.class)
    public void applicationStartedEvent() {
        LOGGER.info("Success");

    }

}
