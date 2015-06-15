package by.leonovich.booklib.dao;

import by.leonovich.booklib.domain.Book;
import by.leonovich.booklib.util.Constants;
import by.leonovich.booklib.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static by.leonovich.booklib.util.Constants.ConstList.BOOK_DAO_BEAN;
import static by.leonovich.booklib.util.Constants.ConstList.SPRING_SETTINGS;
import static org.junit.Assert.*;

/**
 * Created by alexanderleonovich on 12.06.15.
 */
public class BookDaoTest {

    private static ClassPathXmlApplicationContext ac;
    private SessionFactory sessionFactory;
    private Session session;
    private BookDao bookDao;
    private Book book;


    @Before
    public void setUp() throws Exception {
        ac = new ClassPathXmlApplicationContext(new String[]{SPRING_SETTINGS});
        bookDao = (BookDao) ac.getBean(BOOK_DAO_BEAN);
        book = (Book) ac.getBean("bookEntity");
        sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
        session = sessionFactory.openSession();
    }

    @After
    public void tearDown() throws Exception {
        book = null;
        session.close();
    }

    @Test
    public void testSave() throws Exception {
        assertNull("Id before save() is not null.", book.getBookId());
        bookDao.save(book);
        assertNotNull("After save() person and address person-personId is null. ", book.getBookId());
    }

    @Test
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

    }
}