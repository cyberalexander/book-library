package by.leonovich.booklibrary.services;

import by.leonovich.booklibrary.domain.Book;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alexanderleonovich on 12.06.15.
 */
public interface BookService {

    Serializable createBook();

    Book findBook();

    List<Book> getBooks();

    void addBooks(File file);

    void deleteBook();

}
