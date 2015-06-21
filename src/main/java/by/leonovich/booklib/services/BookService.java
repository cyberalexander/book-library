package by.leonovich.booklib.services;

import by.leonovich.booklib.dao.BookDao;
import by.leonovich.booklib.dao.exception.DaoException;
import by.leonovich.booklib.domain.Book;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static by.leonovich.booklib.util.Constants.ConstList.*;

/**
 * Created by alexanderleonovich on 12.06.15.
 * Service methods for working with Book-entity
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class BookService implements IBookService {
    private static final Logger log = Logger.getLogger(BookService.class);

    @Autowired
    private BookDao bookDao;

    public BookService() {
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
        System.out.print(WRITE_TITLE);
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
            log.error(e);
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
            log.error(e);
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
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
        return list;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
