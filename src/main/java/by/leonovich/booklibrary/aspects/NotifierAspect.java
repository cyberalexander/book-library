package by.leonovich.booklibrary.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This is a Spring bean/aspect registered using annotations {@link Aspect} and {@link Component}.
 * More about Spring AOP concepts in <a href="https://docs.spring.io/spring-framework/docs/4.3.12.RELEASE/spring-framework-reference/html/aop.html">official documentation</a>
 * Created by alexanderleonovich on 11.06.15.
 */
@Aspect
@Component
public class NotifierAspect {
    private static final Logger log = LoggerFactory.getLogger(NotifierAspect.class);

    @Pointcut("execution(* *.findBook())")
    public void findBookPointCut() {
    }

    @Before("findBookPointCut()")
    public void notifyBefore() {
        log.info("Notification before method >>> ");
    }

    @After("findBookPointCut()")
    public void notifyAfter() {
        log.info("Notification after method >>> ");
    }

    @AfterThrowing("findBookPointCut()")
    public void notifyAfterException() {
        log.info("Notification after throws Exception >>> ");
    }
}