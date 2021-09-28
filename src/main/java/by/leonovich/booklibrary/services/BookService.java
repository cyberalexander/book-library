package by.leonovich.booklibrary.services;

import by.leonovich.booklibrary.dao.exception.DaoException;
import by.leonovich.booklibrary.domain.Book;

import java.io.File;
import java.util.List;

/**
 * Created by alexanderleonovich on 12.06.15.
 */
public interface BookService {

    Book createBook(Book book) throws DaoException;

    Book findBook();

    List<Book> getBooks();

    void addBooks(File file) throws DaoException;

    void deleteBook() throws DaoException;

}
