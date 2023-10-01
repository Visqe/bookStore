package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.cart.CartResponseDto;
import mate.academy.bookstore.dto.cart.CreateCartItemRequestDto;
import mate.academy.bookstore.dto.cart.UpdateCartItemRequestDto;
import org.springframework.data.domain.Pageable;

public interface CartService {
    CartResponseDto addItem(Long userId, CreateCartItemRequestDto createCartItemRequestDto);

    CartResponseDto update(Long userId, Long id, UpdateCartItemRequestDto createCartItemRequestDto);

    CartResponseDto getAll(Long userId, Pageable pageable);
}
