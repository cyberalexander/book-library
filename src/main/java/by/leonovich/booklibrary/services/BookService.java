package by.leonovich.booklibrary.services;

import by.leonovich.booklibrary.domain.Book;
import by.leonovich.booklibrary.services.exception.ServiceException;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alexanderleonovich on 12.06.15.
 */
public interface BookService {

    Serializable createBook(Book book) throws ServiceException;

    Book findBook() throws ServiceException;

    List<Book> getBooks() throws ServiceException;

    void addBooks(File file) throws ServiceException;

    void deleteBook() throws ServiceException;

}
