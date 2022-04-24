/*
 * MIT License
 *
 * Copyright (c) 2015-2022 Aliaksandr Leanovich. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR
 * IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package by.leonovich.booklibrary.aspects;

import by.leonovich.booklibrary.domain.Book;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Spring Aspect demo to show the basic abilities of Spring AOP.
 * Created by alexanderleonovich on 12.06.15.
 */
@Log4j2
@Aspect
@Component
public class WorkAspect {

    @Pointcut("execution(* by.leonovich.booklibrary.service.BookService.createBook())")
    public void performance() {
    }

    @Pointcut("execution(* by.leonovich.booklibrary.repository.BookRepository.save(*)) && args(book)")
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
            if (log.isInfoEnabled()) {
                log.info("Aspect AROUND : END {}", stopWatch.getTime(TimeUnit.MILLISECONDS));
            }
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    @Before(value = "intercept(book)", argNames = "book")
    public void interceptWorkName(Book book) {
        log.info("Input book is {}", book);
    }
}
