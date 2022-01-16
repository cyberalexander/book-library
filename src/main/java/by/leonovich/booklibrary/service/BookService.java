package by.leonovich.booklibrary.service;

import by.leonovich.booklibrary.domain.Book;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * An interface dedicated to provide an API to operate with {@link Book} entity.
 * <p>
 * Created by alexanderleonovich on 12.06.15.
 */
public interface BookService {

    Serializable createBook();

    Optional<Book> findBook();

    List<Book> getBooks();

    void addBooks(File file);

    void deleteBook();

}
