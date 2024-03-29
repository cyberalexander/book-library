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

import by.leonovich.booklibrary.service.BookService;
import by.leonovich.booklibrary.util.ConsoleScanner;
import by.leonovich.booklibrary.util.Constants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Scanner;

/**
 * Created : 07/01/2022 10:46
 * Project : book-library
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
@Configuration
public class BookLibraryApplicationConfiguration {

    @Bean(destroyMethod = "close")
    public ConsoleScanner scanner() {
        return new ConsoleScanner(new Scanner(System.in));
    }

    @Bean
    public CommandLineRunner dataLoader(BookService bookService) {
        return args -> {
            File file = new File(BookLibraryApplication.class.getClassLoader().getResource(Constants.FILE).getPath());
            bookService.addBooks(file);
        };
    }
}
