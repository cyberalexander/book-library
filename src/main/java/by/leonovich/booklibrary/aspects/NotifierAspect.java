package by.leonovich.booklibrary.aspects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is a Spring bean registered in {@see spring.cfg.xml}
 * and it's a part of an Aspect, which is also registered in {@see spring.cfg.xml}
 * Created by alexanderleonovich on 11.06.15.
 */
public class NotifierAspect {
    private static final Logger log = LoggerFactory.getLogger(NotifierAspect.class);

    public void notifyBefore() {
        log.info("Notification before method >>> ");
    }

    public void notifyAfter() {
        log.info("Notification after method >>> ");
    }

    public void notifyAfterException() {
        log.info("Notification after throws Exception >>> ");
    }
}
