package by.leonovich.booklibrary.repository;

import by.leonovich.booklibrary.domain.Book;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring JPA repository implementation called for work with {@link Book} persistent entity.
 * <p>
 * Created by alexanderleonovich on 12.06.15.
 * Specified for Book-entity dao-layer
 */
public interface BookRepository extends CrudRepository<Book, Long> {

}
