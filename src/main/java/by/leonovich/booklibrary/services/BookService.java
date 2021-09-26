package by.leonovich.booklibrary.services;

import by.leonovich.booklibrary.dao.BookDao;
import by.leonovich.booklibrary.dao.exception.DaoException;
import by.leonovich.booklibrary.domain.Book;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static by.leonovich.booklibrary.util.Constants.ConstList.WRITE_AUTHOR;
import static by.leonovich.booklibrary.util.Constants.ConstList.WRITE_ID;
import static by.leonovich.booklibrary.util.Constants.ConstList.WRITE_TITLE;
import static by.leonovich.booklibrary.util.Constants.ConstList.WRITE_YEAR;

/**
 * Created by alexanderleonovich on 12.06.15.
 * Service methods for working with Book-entity
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class BookService implements IBookService {
    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    private final BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book createBook(Book book) throws DaoException {
        System.out.println("Please enter book description:");
        if (book == null) {
            book = new Book();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print(WRITE_TITLE);
        String parameter = scanner.nextLine();
        book.setTitle(parameter);
        System.out.print(WRITE_AUTHOR);
        parameter = scanner.nextLine();
        book.setAuthor(parameter);
        System.out.print(WRITE_YEAR);
        parameter = scanner.nextLine();
        book.setYear(parameter);
        bookDao.save(book);
        return book;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Book findBook() {
        System.out.println("Get by Id. Please enter book id:");
        System.out.print(WRITE_ID);

        Scanner scanner = new Scanner(System.in);
        Book book = null;
        Long id = scanner.nextLong();
        try {
            book = bookDao.get(id);
        } catch (DaoException e) {
            log.error("Failed to get book.", e);
        }
        System.out.print(book);
        return book;
    }

    @Override
    public List<Book> getBooks() {
        List<Book> list = null;
        try {
            list = bookDao.getAll();
            for (Book element : list) {
                System.out.println(element.toString());
            }
        } catch (DaoException e) {
            log.error("Failed to get books.", e);
        }
        return list;
    }

    public void addBooks(File file) throws DaoException {
        List<String> books = parseFile(file);
        for (String element : books) {
            Book book = new Book();
            String[] parameters = element.split(", ");
            book.setTitle(parameters[0]);
            book.setAuthor(parameters[1]);
            book.setYear(parameters[2]);
            bookDao.save(book);
        }
    }

    @Override
    public void deleteBook() throws DaoException {
        Book book = findBook();
        bookDao.delete(book);
    }


    private List<String> parseFile(File file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    list.add(line);
                }else {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + file.getPath() + " not found.", e);
        } catch (IOException e) {
            throw new RuntimeException("Exception while reading the file " + file.getPath(), e);
        }
        return list;
    }
}
