package mate.academy.bookstore;

import java.math.BigDecimal;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreApplication {

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Book book1 = new Book();
            book1.setTitle("book1");
            book1.setAuthor("author1");
            book1.setIsbn("isbn1");
            book1.setPrice(BigDecimal.valueOf(111));
            bookService.save(book1);

            Book book2 = new Book();
            book2.setTitle("book2");
            book2.setAuthor("author2");
            book2.setIsbn("isbn2");
            book2.setPrice(BigDecimal.valueOf(222));
            bookService.save(book2);

            System.out.println(bookService.getAll());
        };
    }
}
