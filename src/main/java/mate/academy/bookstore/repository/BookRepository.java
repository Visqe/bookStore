package mate.academy.bookstore.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.bookstore.model.Book;
import org.springframework.stereotype.Component;

@Component
public interface BookRepository {
    Book save(Book book);

    List<Book> getAll();

    Optional<Book> findById(Long id);
}
