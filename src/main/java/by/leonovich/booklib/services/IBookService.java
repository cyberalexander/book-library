package by.leonovich.booklib.services;

import by.leonovich.booklib.dao.exception.DaoException;
import by.leonovich.booklib.domain.Book;

import java.io.File;
import java.util.List;

/**
 * Created by alexanderleonovich on 12.06.15.
 */
public interface IBookService {

    Book createBook(Book book) throws DaoException;

    Book findBook();

    List<Book> getBooks();

    void addBooks(File file) throws DaoException;

    void deleteBook() throws DaoException;

}
