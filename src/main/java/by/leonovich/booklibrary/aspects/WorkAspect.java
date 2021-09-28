package by.leonovich.booklibrary.aspects;

import by.leonovich.booklibrary.domain.Book;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Spring Aspect demo to show the basic abilities of Spring AOP.
 * Created by alexanderleonovich on 12.06.15.
 */
@Aspect
@Component
public class WorkAspect {
    private static final Logger log = LoggerFactory.getLogger(WorkAspect.class);

    @Pointcut("execution(* by.leonovich.booklibrary.services.BookService.createBook(by.leonovich.booklibrary.domain.Book))")
    public void performance() {
    }

    @Pointcut("execution(* by.leonovich.booklibrary.dao.AbstractDao.save(*)) && args(book)")
    public void intercept(Book book) {
    }

    @Before("performance()")
    public void before() {
        log.info("Aspect BEFORE");
    }

    @AfterReturning("performance()")
    public void afterReturning() {
        log.info("Aspect AFTER RETURN");
    }

    @After("performance()")
    public void after() {
        log.info("Aspect AFTER");
    }

    @AfterThrowing("performance()")
    public void afterError() {
        log.info("After ERROR");
    }

    @Around("performance()")
    public void aroundWork(ProceedingJoinPoint joinPoint) {
        try {
            log.info("Aspect AROUND : BEGIN");
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            joinPoint.proceed();
            stopWatch.stop();
            log.info("Aspect AROUND : END {}", stopWatch.getTime(TimeUnit.MILLISECONDS));
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    @Before(value = "intercept(book)", argNames = "book")
    public void interceptWorkName(Book book) {
        log.info("Input book is {}", book);
    }
}
