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
package by.leonovich.booklibrary.service;

import by.leonovich.booklibrary.domain.Book;
import by.leonovich.booklibrary.repository.BookRepository;
import by.leonovich.booklibrary.util.ConsoleScanner;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

/**
 * Created : 11/01/2022 09:31
 * Project : book-library
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
@Log4j2
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    private static final Book TEST_BOOK = new Book("test_title", "test_author", "2022");

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookRepository repositoryMock;
    @Mock
    private ConsoleScanner scannerMock;

    @Test
    void createBook() {
        Mockito.when(scannerMock.nextLine()).thenReturn(TEST_BOOK.getTitle());
        Mockito.when(scannerMock.nextLine()).thenReturn(TEST_BOOK.getAuthor());
        Mockito.when(scannerMock.nextLine()).thenReturn(TEST_BOOK.getYear());
        Mockito.when(repositoryMock.save(Mockito.any())).thenReturn(TEST_BOOK);

        Book actual = bookService.createBook();

        Assertions.assertEquals(TEST_BOOK, actual, String.format("%s is not equal to %s", TEST_BOOK, actual));
        Mockito.verify(scannerMock, new Times(4)).nextLine();
        Mockito.verify(repositoryMock).save(Mockito.any());
    }

    @Test
    void findBook() {
        Optional<Book> expected = Optional.of(TEST_BOOK);
        Mockito.when(scannerMock.nextLong()).thenReturn(Long.MAX_VALUE);
        Mockito.when(repositoryMock.findById(Mockito.any())).thenReturn(expected);

        Optional<Book> actual = bookService.findBook();

        Assertions.assertEquals(expected, actual, String.format("%s is not equal to %s", expected, actual));
        Mockito.verify(scannerMock).nextLong();
        Mockito.verify(repositoryMock).findById(Mockito.any());
    }

    @Test
    void getBooks() {
        List<Book> expected = List.of(TEST_BOOK);
        Mockito.when(repositoryMock.findAll()).thenReturn(expected);

        List<Book> actual = bookService.getBooks();

        Assertions.assertEquals(expected, actual, String.format("%s is not equal to %s", expected, actual));
        Mockito.verify(repositoryMock).findAll();
    }

    @Test
    void deleteBook() {
        Optional<Book> expected = Optional.of(TEST_BOOK);
        Mockito.when(scannerMock.nextLong()).thenReturn(Long.MAX_VALUE);
        Mockito.when(repositoryMock.findById(Mockito.any())).thenReturn(expected);

        bookService.deleteBook();

        Mockito.verify(scannerMock).nextLong();
        Mockito.verify(repositoryMock).delete(TEST_BOOK);
    }
}