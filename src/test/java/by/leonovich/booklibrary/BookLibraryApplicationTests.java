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
package by.leonovich.booklibrary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created : 07/01/2022 10:42
 * Project : book-library
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
@SpringBootTest
@ActiveProfiles("test")
class BookLibraryApplicationTests {

    @Value("${application.some.parameter}")
    private String someParameter;

    @Value("${application.some.other.parameter}")
    private String someOtherParameter;

    @Test
    void contextLoads() {
        //Default unit test verifying if Spring Boot Context initialization successful
    }

    /**
     * Parameter overridden in {@see application-test.yaml} and
     * the value expected to be assigned from {@see application-test.yaml}
     */
    @Test
    void testParameterValueTakenFromTestProperties() {
        Assertions.assertEquals("some test value", someParameter);
    }

    /**
     * Parameter exists only in default application.yaml and the value expected to be taken from there.
     */
    @Test
    void testParameterValueTakenFromDefaultProperties() {
        Assertions.assertEquals("another real value", someOtherParameter);
    }
}
