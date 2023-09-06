package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.BookResponseDto;
import mate.academy.bookstore.dto.CreateBookRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    BookResponseDto save(CreateBookRequestDto createBookRequestDto);

    List<BookResponseDto> getAll();

    BookResponseDto getById(Long id);

    void deleteById(Long id);
}
