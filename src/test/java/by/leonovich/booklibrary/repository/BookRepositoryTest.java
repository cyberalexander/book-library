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
package by.leonovich.booklibrary.repository;

import by.leonovich.booklibrary.domain.Book;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * The unit tests of the data access layer, Spring JPA, to be precise.
 * <p>
 * Created by alexanderleonovich on 12.06.15.
 */
@Log4j2
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Autowired
    private CrudRepository<Book, Long> repository;
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book("test_title", "test_author", "2022");
    }

    @Test
    void testSave() {
        repository.save(book);
        Assertions.assertNotNull(book.getBookId(), "bookId should not be null.");
    }

    @Test
    void testGet() {
        repository.save(book);
        Optional<Book> queried = repository.findById(book.getBookId());
        Assertions.assertFalse(queried.isEmpty(), String.format("The book %s should be present in database", book));
        Assertions.assertEquals(book, queried.get(), String.format("%s should be equal to %s", book, queried.get()));
    }

    @Test
    void testDelete() {
        repository.save(book);
        repository.delete(book);
        Optional<Book> queried = repository.findById(book.getBookId());
        Assertions.assertTrue(queried.isEmpty(), "Deleted book should not be present in database.");
    }

    @Test
    void testGetAll() {
        Iterable<Book> allBooks = repository.findAll();
        Assertions.assertTrue(allBooks.iterator().hasNext(), "The books does not exists in database.");
    }

    @Test
    void testUpdate() {
        repository.save(book);
        String newValue = "test_author_updated";
        book.setAuthor(newValue);
        repository.save(book);
        Optional<Book> queried = repository.findById(book.getBookId());
        Assertions.assertFalse(queried.isEmpty(), String.format("The book %s should be present in database", book));
        Assertions.assertEquals(
            book.getAuthor(),
            queried.get().getAuthor(),
            String.format("%s should be equal to %s", book.getAuthor(), queried.get().getAuthor())
        );
    }
}