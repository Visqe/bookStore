package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.book.BookResponseDtoWithoutCategoryId;
import mate.academy.bookstore.dto.category.CategoryResponseDto;
import mate.academy.bookstore.dto.category.CreateCategoryRequestDto;
import mate.academy.bookstore.service.BookService;
import mate.academy.bookstore.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Controller management",
        description = "Endpoints for managing controller (CRUD operations)")
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new Category", description = "create a new category")
    public CategoryResponseDto save(
            @RequestBody CreateCategoryRequestDto createCategoryRequestDto) {
        return categoryService.save(createCategoryRequestDto);
    }

    @GetMapping
    @Operation(summary = "Find Categories", description = "find all categories")
    public List<CategoryResponseDto> getAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "find Category", description = "get Categoru by id")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update Category", description = "update category by id")
    public CategoryResponseDto update(@PathVariable Long id,
                          @RequestBody CreateCategoryRequestDto createCategoryRequestDto) {
        return categoryService.update(id, createCategoryRequestDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Category", description = "delete category by id")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @GetMapping("/{id}/books")
    @Operation(summary = "Find Category", description = "get Category by id")
    public List<BookResponseDtoWithoutCategoryId> findBooksByCategoryId(
            @PathVariable Long id, Pageable pageable
    ) {
        return bookService.findBooksByCategoryId(id, pageable);
    }

}
