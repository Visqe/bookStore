package mate.academy.bookstore.service.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.cart.CartResponseDto;
import mate.academy.bookstore.dto.cart.CreateCartItemRequestDto;
import mate.academy.bookstore.dto.cart.UpdateCartItemRequestDto;
import mate.academy.bookstore.mapper.CartItemMapper;
import mate.academy.bookstore.mapper.CartMapper;
import mate.academy.bookstore.model.Cart;
import mate.academy.bookstore.model.CartItem;
import mate.academy.bookstore.repository.CartItemRepository;
import mate.academy.bookstore.repository.CartRepository;
import mate.academy.bookstore.service.CartService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Override
    public CartResponseDto addItem(
            Long userId,
            CreateCartItemRequestDto createCartItemRequestDto
    ) {
        CartItem newCartItem = cartItemMapper.toModel(createCartItemRequestDto);
        Cart usersCart = cartRepository.findByUserId(userId);
        newCartItem.setCart(usersCart);

        if (!cartItemRepository.findAllByCartIdAndBookId(
                usersCart.getId(), createCartItemRequestDto.getBookId()
                ).isEmpty()
        ) {
            throw new RuntimeException("This book already exist in your cart");
        }
        cartItemRepository.save(newCartItem);

        usersCart = cartRepository.findByUserId(userId);
        return cartMapper.toResponseDto(usersCart);
    }

    @Override
    public CartResponseDto update(
            Long userId,
            Long cartItemId,
            UpdateCartItemRequestDto updateCartItemRequestDto
    ) {
        CartItem cartItemToUpdate = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Unexisted cartItemId to update"));
        if (!Objects.equals(userId, cartItemToUpdate.getCart().getUser().getId())) {
            throw new RuntimeException("You have not access to this cart item");
        }
        cartItemToUpdate.setQuantity(updateCartItemRequestDto.getQuantity());
        cartItemRepository.save(cartItemToUpdate);

        Cart usersCart = cartRepository.findByUserId(userId);
        return cartMapper.toResponseDto(usersCart);
    }

    @Override
    public CartResponseDto getAll(
            Long userId,
            Pageable pageable
    ) {
        Cart usersCart = cartRepository.findByUserId(userId);
        return cartMapper.toResponseDto(usersCart);
    }
}
