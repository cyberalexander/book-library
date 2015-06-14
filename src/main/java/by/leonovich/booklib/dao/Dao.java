package by.leonovich.booklib.dao;

import by.leonovich.booklib.dao.exception.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by alexanderleonovich on 11.06.15.
 * Abstract dao layer
 */
@Repository
public abstract class Dao<T> implements IDao<T> {
    protected SessionFactory sessionFactory;

    public Dao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void save(T t) throws DaoException {
        Session session = getSession();
        session.save(t);
    }

    @Override
    public void saveOrUpdate(T t) throws DaoException {
        Session session = getSession();
        session.saveOrUpdate(t);
    }

    @Override
    public T get(Serializable id) throws DaoException {
        Session session = getSession();
        T t = (T) session.get(getPersistentClass(), id);
        return t;
    }

    @Override
    public T load(Serializable id) throws DaoException {
        Session session = getSession();
        T t = (T) session.load(getPersistentClass(), id);
        return t;
    }

    @Override
    public void delete(T t) throws DaoException {
        Session session = getSession();
        session.delete(t);
    }

    @Override
    public List<T> getAll() throws DaoException {
        Session session = getSession();
        List<T> list = parseResultForGetAll(session);
        return list;
    }

    @Override
    public void update(T t) throws DaoException {
            Session session = getSession();
            session.update(t);
    }

    protected abstract List<T> parseResultForGetAll(Session session);

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
