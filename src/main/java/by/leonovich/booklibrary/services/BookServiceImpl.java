package by.leonovich.booklibrary.services;

import by.leonovich.booklibrary.dao.BookDao;
import by.leonovich.booklibrary.dao.exception.DaoException;
import by.leonovich.booklibrary.domain.Book;
import by.leonovich.booklibrary.services.exception.ServiceException;
import by.leonovich.booklibrary.util.Constants;
import by.leonovich.booklibrary.util.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.out;

/**
 * Created by alexanderleonovich on 12.06.15.
 * Service methods for working with Book-entity
 */
@Service("bookService")
@Transactional(propagation = Propagation.REQUIRED)
public class BookServiceImpl implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Serializable createBook(Book book) throws ServiceException {
        out.println("Please enter book description:");
        if (book == null) {
            book = new Book();
        }
        Scanner scanner = new Scanner(System.in);
        out.print(Constants.WRITE_TITLE);
        String parameter = scanner.nextLine();
        book.setTitle(parameter);
        out.print(Constants.WRITE_AUTHOR);
        parameter = scanner.nextLine();
        book.setAuthor(parameter);
        out.print(Constants.WRITE_YEAR);
        parameter = scanner.nextLine();
        book.setYear(parameter);
        Serializable bookId;
        try {
            bookId = bookDao.save(book);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return bookId;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Book findBook() throws ServiceException {
        out.println("Get by Id. Please enter book id:");
        out.print(Constants.WRITE_ID);

        Scanner scanner = new Scanner(System.in);
        Book book;
        Long id = scanner.nextLong();
        try {
            book = bookDao.get(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        out.print(book);
        return book;
    }

    @Override
    public List<Book> getBooks() throws ServiceException {
        List<Book> list = null;
        try {
            list = bookDao.getAll();
            for (Book element : list) {
                out.println(element.toString());
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return list;
    }

    public void addBooks(File file) throws ServiceException {
        parseFileLines(file).stream()
            .map(line -> {
                Book book = new Book();
                String[] parameters = line.split(", ");
                book.setTitle(parameters[0]);
                book.setAuthor(parameters[1]);
                book.setYear(parameters[2]);
                return book;
            })
            .map(book -> Try.of(() -> bookDao.save(book)))
            .forEach(out::println);
    }

    @Override
    public void deleteBook() throws ServiceException {
        Book book = findBook();
        try {
            bookDao.delete(book);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    private List<String> parseFileLines(File file) {
        /*
        * If you want to return Stream instead of List, then do not use try-with-resources, do not close reader here
        */
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return bufferedReader.lines().toList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + file.getPath() + " not found.", e);
        } catch (IOException e) {
            throw new RuntimeException("Exception while reading the file " + file.getPath(), e);
        }
    }
}
