package by.leonovich.booklibrary.dao.exception;

/**
 * Created by alexanderleonovich on 11.06.15.
 */
public class DaoException extends Exception {

    private Exception exception;

    public DaoException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
