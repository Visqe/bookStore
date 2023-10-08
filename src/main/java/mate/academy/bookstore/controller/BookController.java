package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.book.BookResponseDto;
import mate.academy.bookstore.dto.book.CreateBookRequestDto;
import mate.academy.bookstore.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management", description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new Book", description = "create a new book")
    public BookDto save(@RequestBody @Valid CreateBookRequestDto createBookRequestDto) {
        return bookService.save(createBookRequestDto);
    }

    @GetMapping
    @Operation(summary = "Get Books", description = "get all books")
    public List<BookResponseDto> getAll(Pageable pageable) {
        return bookService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Book", description = "get book by id")
    public BookResponseDto getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Book", description = "update book by id")
    public BookDto update(@PathVariable Long id,
                          @Valid @RequestBody CreateBookRequestDto createBookRequestDto) {
        return bookService.update(id, createBookRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Book", description = "delete book by id")
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
