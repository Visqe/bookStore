package mate.academy.bookstore.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.book.BookDto;
import mate.academy.bookstore.dto.book.BookResponseDtoWithoutCategoryId;
import mate.academy.bookstore.dto.book.CreateBookRequestDto;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    @Mapping(target = "categoryIds", ignore = true)
    BookDto toDto(Book book);

    @Mapping(target = "categories", ignore = true)
    Book toModel(CreateBookRequestDto createBookRequestDto);

    BookResponseDtoWithoutCategoryId toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCaterogiesIds(@MappingTarget BookDto bookDto, Book book) {
        Set<Category> categories = book.getCategories();
        if (categories != null) {
            bookDto.setCategoryIds(categories.stream()
                    .map(Category::getId)
                    .collect(Collectors.toSet()));
        }
    }

    @AfterMapping
    default void setCaterogies(
            @MappingTarget Book book,
            CreateBookRequestDto createBookRequestDto
    ) {
        Set<Long> categoryIds = createBookRequestDto.getCategoryIds();
        if (categoryIds != null) {
            book.setCategories(categoryIds.stream()
                    .map(Category::new)
                    .collect(Collectors.toSet()));
        }
    }
}
