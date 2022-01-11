package by.leonovich.booklibrary;

import by.leonovich.booklibrary.service.BookService;
import by.leonovich.booklibrary.util.ConsoleScanner;
import by.leonovich.booklibrary.util.Constants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Scanner;

/**
 * Created : 07/01/2022 10:46
 * Project : book-library
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
@Configuration
public class BookLibraryApplicationConfiguration {

    @Bean(destroyMethod = "close")
    public ConsoleScanner scanner() {
        return new ConsoleScanner(new Scanner(System.in));
    }

    @Bean
    public CommandLineRunner dataLoader(BookService bookService) {
        return args -> {
            File file = new File(BookLibraryApplication.class.getClassLoader().getResource(Constants.FILE).getPath());
            bookService.addBooks(file);
        };
    }
}
