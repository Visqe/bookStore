package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.book.BookResponseDto;
import mate.academy.bookstore.dto.book.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    BookResponseDto save(CreateBookRequestDto createBookRequestDto);

    List<BookResponseDto> getAll(Pageable pageable);

    BookResponseDto getById(Long id);

    void deleteById(Long id);

    BookDto update(Long id, CreateBookRequestDto createBookRequestDto);
}
