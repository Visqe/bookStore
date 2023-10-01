package mate.academy.bookstore.mapper;

import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.cart.CartItemResponseDto;
import mate.academy.bookstore.dto.cart.CreateCartItemRequestDto;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.CartItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    @Mapping(source = "cart.id", target = "cartId")
    CartItemResponseDto toResponseDto(CartItem cartItem);

    CartItem toModel(CreateCartItemRequestDto createCartItemRequestDto);

    @AfterMapping
    default void setBooks(
            @MappingTarget CartItem cartItem,
            CreateCartItemRequestDto createCartItemRequestDto
    ) {
        Long bookId = createCartItemRequestDto.getBookId();
        cartItem.setBook(new Book(bookId));
    }
}
