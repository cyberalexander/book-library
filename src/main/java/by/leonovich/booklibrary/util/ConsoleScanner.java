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
package by.leonovich.booklibrary.util;

import lombok.extern.log4j.Log4j2;

import java.util.Scanner;

/**
 * This is a wrapper class on {@link Scanner} class. Created for the purpose of unit-testing,
 * as {@link Scanner} is a final class and cannot be simply "mocked" without additional effort.
 * <p>
 * The idea of this wrapper-scanner taken from  the following
 * <a href="https://gist.github.com/JordanTFA/8e6f32bf1a114eed48c762c7fda4d5e8">gist</a>.
 * <p>
 * Created : 10/01/2022 09:00
 * Project : book-library
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
@Log4j2
public class ConsoleScanner {
    private final Scanner scanner;

    public ConsoleScanner(final Scanner s) {
        this.scanner = s;
    }

    public int nextInt() {
        return scanner.nextInt();
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public long nextLong() {
        return scanner.nextLong();
    }

    public void close() {
        if (log.isWarnEnabled()) {
            log.warn("{} closed!", this.scanner.getClass());
        }
        this.scanner.close();
    }
}