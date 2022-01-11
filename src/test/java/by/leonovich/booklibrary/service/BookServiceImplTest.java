package by.leonovich.booklibrary.service;

import by.leonovich.booklibrary.domain.Book;
import by.leonovich.booklibrary.repository.BookRepository;
import by.leonovich.booklibrary.util.ConsoleScanner;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Created : 11/01/2022 09:31
 * Project : book-library
 * IDE : IntelliJ IDEA
 *
 * @author alexanderleonovich
 * @version 1.0
 */
@Log4j2
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookRepository repositoryMock;
    @Mock
    private ConsoleScanner scannerMock;

    @Test
    void createBook() {
        Book expected = new Book("test_title", "test_author", "2022");
        Mockito.when(scannerMock.nextLine()).thenReturn(expected.getTitle());
        Mockito.when(scannerMock.nextLine()).thenReturn(expected.getAuthor());
        Mockito.when(scannerMock.nextLine()).thenReturn(expected.getYear());
        Mockito.when(repositoryMock.save(Mockito.any())).thenReturn(expected);

        Book actual = bookService.createBook();

        Assertions.assertEquals(expected, actual, String.format("%s is not equal to %s", expected, actual));
        Mockito.verify(scannerMock, new Times(3)).nextLine();
        Mockito.verify(repositoryMock).save(Mockito.any());
    }

    @Test
    void findBook() {
        //TODO implement findBook() unit-test
    }

    @Test
    void getBooks() {
        //TODO implement getBooks() unit-test
    }

    @Test
    void deleteBook() {
        //TODO implement deleteBook() unit-test
    }
}