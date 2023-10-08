package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.BookDto;
import mate.academy.bookstore.dto.CreateBookRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    BookDto save(CreateBookRequestDto createBookRequestDto);

    List<BookDto> getAll();

    BookDto getById(Long id);
}
