package by.leonovich.booklib.dao;

import by.leonovich.booklib.domain.Book;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alexanderleonovich on 12.06.15.
 * Specified for Book-entity dao-layer
 */
@Repository
public class BookDao extends Dao<Book> {

    public BookDao() {
        super();
    }

    /**
     * Method - parser for table books
     * @param session
     * @return
     */
    @Override
    protected List<Book> parseResultForGetAll(Session session) {
        List<Book> list;
        String hql = "SELECT b FROM Book b";
        Query query = session.createQuery(hql);
        list = query.list();
        return list;
    }
}
