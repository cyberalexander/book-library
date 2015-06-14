package by.leonovich.booklib.main;

import by.leonovich.booklib.dao.exception.DaoException;
import by.leonovich.booklib.domain.Book;
import by.leonovich.booklib.services.IBookService;
import by.leonovich.booklib.util.Constants;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.Scanner;

import static by.leonovich.booklib.util.Constants.ConstList.BOOK_SERVICE_BEAN;
import static by.leonovich.booklib.util.Constants.ConstList.FILE;
import static by.leonovich.booklib.util.Constants.ConstList.SPRING_SETTINGS;

/**
 * Hello world!
 */
public class App {

    private static Boolean needMenu = true;
    private static IBookService bookService;
    private static File file;
    private static ClassPathXmlApplicationContext ac;

    static {
        file = new File(App.class.getClassLoader().getResource(FILE).getPath());
        ac = new ClassPathXmlApplicationContext(new String[]{SPRING_SETTINGS});
        bookService = (IBookService) ac.getBean(BOOK_SERVICE_BEAN);
    }

    public static void main(String[] args) {
        try {
            menu();
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    public static void menu() throws DaoException {
        Book book = null;
        while (needMenu) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    // Add book
                    book = bookService.createBook(book);
                    break;
                case 2:
                    //Delete book
                    bookService.deleteBook();
                    break;
                case 3:
                    // Get Books
                    bookService.getBooks();
                    break;
                case 4:
                    // Get Book
                    bookService.findBook();
                    break;
                case 5:
                    // Parse file and write content in database
                    bookService.addBooks(file);
                    break;
                default:
                    needMenu = true;
                    break;
            }
            needMenu = true;
        }
    }

    private static void printMenu() {
        System.out.println("\n+-------------------------------------------------------+");
        System.out.println("|        Hello, user! You are in menu. Do action:         |");
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|  0. Exit     |      1. Add book  |     2. Delete book   |      3. Get Books   |    4. Get Book  |  5. Parse file & write content in db |");
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------+");

    }
}
