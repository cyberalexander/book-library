package by.leonovich.booklibrary.main;

import by.leonovich.booklibrary.domain.Book;
import by.leonovich.booklibrary.services.BookService;
import by.leonovich.booklibrary.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.Scanner;

import static by.leonovich.booklibrary.util.Constants.ConstList.BOOK_SERVICE_BEAN;
import static by.leonovich.booklibrary.util.Constants.ConstList.FILE;
import static by.leonovich.booklibrary.util.Constants.ConstList.SPRING_SETTINGS;
import static java.lang.System.out;


public class App {
    protected static final Logger LOG = LoggerFactory.getLogger(App.class);

    private static boolean needMenu = true;
    private static final BookService bookService;
    private static File file;
    private static final ClassPathXmlApplicationContext ac;

    static {
        file = new File(App.class.getClassLoader().getResource(FILE).getPath());
        ac = new ClassPathXmlApplicationContext(SPRING_SETTINGS);
        bookService = (BookService) ac.getBean(BOOK_SERVICE_BEAN);
    }

    public static void main(String[] args) throws Exception {
            LOG.info("Context initialized : {}", ac.getEnvironment());
            menu();
    }

    public static void menu() throws Exception {
        Book book = (Book) ac.getBean(Constants.ConstList.BOOK_BEAN);
        while (needMenu) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            switch (scanner.nextInt()) {
                case 0 -> System.exit(0);
                case 1 -> bookService.createBook(book);
                case 2 -> bookService.deleteBook();
                case 3 -> bookService.getBooks();
                case 4 -> bookService.findBook();
                case 5 -> bookService.addBooks(file);
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
