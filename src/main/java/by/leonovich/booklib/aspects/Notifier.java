package by.leonovich.booklib.aspects;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


/**
 * Created by alexanderleonovich on 11.06.15.
 */
@Component("notifier")
public class Notifier {
    private static final Logger log = Logger.getLogger(Notifier.class);

    public void notifyBefore() {
        log.info("<= Notification before method =>");
    }

    public void notifyAfter() {
        log.info("<= Notification after method =>");
    }

    public void notifyAfterException() {
        log.info("<= Notification after throws Exception =>");
    }
}
