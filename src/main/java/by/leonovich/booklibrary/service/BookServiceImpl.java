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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.System.err;
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
    public Book createBook() {
        out.print(Constants.WRITE_TITLE + scanner.nextLine());
        String title = scanner.nextLine();

        out.print(Constants.WRITE_AUTHOR);
        String author = scanner.nextLine();

        out.print(Constants.WRITE_YEAR);
        String year = scanner.nextLine();

        return repository.save(new Book(title, author, year));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Optional<Book> findBook() {
        out.print(Constants.WRITE_ID);
        Long id = scanner.nextLong();

        Optional<Book> book = repository.findById(id);
        if (book.isEmpty()) {
            err.println("Book with ID:" + id + " not found. Please enter ID of existing book.");
        } else {
            out.println(book.get());
        }
        return book;
    }

    @Override
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        repository.findAll().forEach(book -> {
            books.add(book);
            out.println(book);
        });
        return books;
    }

    @Override
    public void deleteBook() {
        findBook().ifPresent(repository::delete);
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
