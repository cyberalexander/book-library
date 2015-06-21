package by.leonovich.booklib.dao;

import by.leonovich.booklib.domain.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static by.leonovich.booklib.util.Constants.ConstList.BOOK_DAO_BEAN;
import static by.leonovich.booklib.util.Constants.ConstList.SPRING_SETTINGS;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by alexanderleonovich on 12.06.15.
 */
@Ignore
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class BookDaoTest {

    private static ApplicationContext ac;
    private BookDao bookDao;
    private Book book;


    @Before
    public void setUp() throws Exception {
        ac = new ClassPathXmlApplicationContext(new String[]{SPRING_SETTINGS});
        bookDao = (BookDao) ac.getBean(BOOK_DAO_BEAN);
        book = (Book) ac.getBean("bookEntity");
    }

    @After
    public void tearDown() throws Exception {
        book = null;
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void testSave() throws Exception {
        assertNull("Id before save() is not null.", book.getBookId());
        bookDao.save(book);
        assertNotNull("After save() person and address person-personId is null. ", book.getBookId());
    }

    /*@Test
    public void testSaveOrUpdate() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testLoad() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }*/
}