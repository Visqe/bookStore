package mate.academy.bookstore.repository;

import java.util.List;
import mate.academy.bookstore.model.Book;
import org.springframework.stereotype.Component;

@Component
public interface BookRepository {

    Book save(Book book);

    List<Book> findAll();
}
