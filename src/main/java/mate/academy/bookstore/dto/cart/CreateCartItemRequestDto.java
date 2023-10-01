package mate.academy.bookstore.dto.cart;

import lombok.Data;

@Data
public class CreateCartItemRequestDto {
    private Integer quantity;
    private Long bookId;
}
