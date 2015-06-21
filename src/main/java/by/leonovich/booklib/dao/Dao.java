package by.leonovich.booklib.dao;

import by.leonovich.booklib.dao.exception.DaoException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    public Dao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void save(T t) throws DaoException {
        getCurrentSession().save(t);
    }

    @Override
    public void saveOrUpdate(T t) throws DaoException {
        getCurrentSession().saveOrUpdate(t);
    }

    @Override
    public T get(Serializable id) throws DaoException {
        return (T) getCurrentSession().get(getPersistentClass(), id);
    }

    @Override
    public T load(Serializable id) throws DaoException {
        return (T) getCurrentSession().load(getPersistentClass(), id);
    }

    @Override
    public void delete(T t) throws DaoException {
        getCurrentSession().delete(t);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<T> getAll() throws DaoException {
        return parseResultForGetAll(getCurrentSession());
    }

    @Override
    public void update(T t) throws DaoException {
        getCurrentSession().update(t);
    }

    protected abstract List<T> parseResultForGetAll(Session session);

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
