package by.leonovich.booklibrary;

import by.leonovich.booklibrary.services.BookService;
import by.leonovich.booklibrary.util.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

import static java.lang.System.out;

@Log4j2
@SpringBootApplication
public class BookLibraryApplication {
    private static boolean needMenu = true;

    public static void main(final String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BookLibraryApplication.class, args);
        BookService bookService = context.getBean(Constants.BOOK_SERVICE_BEAN, BookService.class);

        log.info("Context initialized : {}", context.getEnvironment());
        menu(bookService);
    }

    public static void menu(BookService bookService) {
        while (needMenu) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            switch (scanner.nextInt()) {
                case 0 -> System.exit(0);
                case 1 -> bookService.createBook();
                case 2 -> bookService.deleteBook();
                case 3 -> bookService.getBooks();
                case 4 -> bookService.findBook();
                default -> out.println("Please make your choice");
            }
            needMenu = true;
        }
    }

    private static void printMenu() {
        out.println("\n+-------------------------------------------------------+");
        out.println("|        Hello, user! You are in menu. Do action:         |");
        out.println("+----------------------------------------------------------------------------------------------------------------------------------------+");
        out.println("|  0. Exit     |      1. Add book  |     2. Delete book   |      3. Get Books   |    4. Get Book  |  5. Parse file & write content in db |");
        out.println("+----------------------------------------------------------------------------------------------------------------------------------------+");

    }
}
