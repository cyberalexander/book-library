package by.leonovich.booklibrary.service;

import by.leonovich.booklibrary.domain.Book;
import by.leonovich.booklibrary.repository.BookRepository;
import by.leonovich.booklibrary.util.ConsoleScanner;
import by.leonovich.booklibrary.util.Constants;
import by.leonovich.booklibrary.util.Try;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Class accumulates methods directed to operations with {@link Book} entity. This class also encapsulates
 * the access to persistent layer, a.k.a data access layer.
 * <p>
 * Created by alexanderleonovich on 12.06.15.
 * Service methods for working with Book-entity
 *
 * @see BookService
 */
@Log4j2
@Service("bookService")
@Transactional(propagation = Propagation.REQUIRED)
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final ConsoleScanner scanner;

    public BookServiceImpl(BookRepository bookRepository, ConsoleScanner consoleScanner) {
        this.repository = bookRepository;
        this.scanner = consoleScanner;
    }

    @Override
    public Serializable createBook() {
        out.println("Please enter book description:");
        Book book = new Book();
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
        bookId = repository.save(book);
        return bookId;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Book findBook() {
        out.println("Get by Id. Please enter book id:");
        out.print(Constants.WRITE_ID);

        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();

        Optional<Book> bookOptional = repository.findById(id);
        out.print(bookOptional.get());
        return bookOptional.get();
    }

    @Override
    public List<Book> getBooks() {
        List<Book> list = new ArrayList<>();
        repository.findAll().forEach(book -> {
            list.add(book);
            out.println(book);
        });
        return list;
    }

    public void addBooks(File file) {
        parseFileLines(file).stream()
            .map(line -> {
                Book book = new Book();
                String[] parameters = line.split(", ");
                book.setTitle(parameters[0]);
                book.setAuthor(parameters[1]);
                book.setYear(parameters[2]);
                return book;
            })
            .map(book -> Try.of(() -> repository.save(book)))
            .forEach(out::println);
    }

    @Override
    public void deleteBook() {
        Book book = findBook();
        repository.delete(book);
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
