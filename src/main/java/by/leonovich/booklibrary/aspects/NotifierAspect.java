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