package by.leonovich.booklibrary.util;

import lombok.extern.log4j.Log4j2;

import java.util.Scanner;

/**
 * This is a wrapper class on {@link Scanner} class. Created for the purpose of unit-testing,
 * as {@link Scanner} is a final class and cannot be simply "mocked" without additional effort.
 * <p>
 * The idea of this wrapper-scanner taken from  the following
 * <a href="https://gist.github.com/JordanTFA/8e6f32bf1a114eed48c762c7fda4d5e8">gist</a>.
 * <p>
 * Created : 10/01/2022 09:00
 * Project : book-library
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
@Log4j2
public class ConsoleScanner implements AutoCloseable {
    private final Scanner scanner;

    public ConsoleScanner(final Scanner s) {
        this.scanner = s;
    }

    public int nextInt() {
        return scanner.nextInt();
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    @Override
    public void close() {
        log.warn("{} closed!", this.scanner.getClass());
        this.scanner.close();
    }
}
