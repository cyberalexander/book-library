package by.leonovich.booklibrary.dao;

import by.leonovich.booklibrary.dao.exception.DaoException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by alexanderleonovich on 11.06.15.
 */
public interface IDao<T> {

    void save(T t) throws DaoException;

    void saveOrUpdate(T t) throws DaoException;

    T get(Serializable id) throws DaoException;

    T load(Serializable id) throws DaoException;

    void delete(T t) throws DaoException;

    List<T> getAll() throws DaoException;

    void update(T t) throws DaoException;

}
