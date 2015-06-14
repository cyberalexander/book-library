package by.leonovich.booklib.aspects;

import by.leonovich.booklib.domain.Book;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by alexanderleonovich on 12.06.15.
 */
@Aspect
public class Work {
    private static final Logger log = Logger.getLogger(Work.class);

    @Pointcut("execution(* by.leonovich.booklib.services.BookService.createBook(by.leonovich.booklib.domain.Book))")
    public void performance() {
    }

    @Pointcut("execution(* by.leonovich.booklib.services.BookService.createBook(by.leonovich.booklib.domain.Book)) && args(book)")
    public void intercept(Book book) {
    }

    @Before("performance()")
    public void beforeWork() {
        log.info("<= !Executing before work! =>");
    }

    @AfterReturning("performance()")
    public void afterWork() {
        log.info("<= !Executing after work! =>");
    }

    @AfterThrowing("performance()")
    public void afterWorkProblems() {
        log.info("<= !Executing after work problems! =>");
    }

    @Around("performance()")
    public void arroundWork(ProceedingJoinPoint joinPoint) {
        try{
            log.info("START");
            Book result = (Book) joinPoint.proceed();
            log.info("FINISH");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Before("intercept(book)")
    public void interceptWorkName(Book book) {
        log.info("WORK NAME IS " + book.toString());
    }

}
