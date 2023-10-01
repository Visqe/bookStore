package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.cart.CartResponseDto;
import mate.academy.bookstore.dto.cart.CreateCartItemRequestDto;
import mate.academy.bookstore.dto.cart.UpdateCartItemRequestDto;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.service.CartService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cart management", description = "Endpoints for managing users cart")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/carts")
public class CartController {
    private final CartService cartService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Add a new item in cart",
            description = "create a new item in users cart"
    )
    public CartResponseDto addItem(
            Authentication authentication,
            @RequestBody @Valid CreateCartItemRequestDto createCartItemRequestDto
    ) {
        Long userId = ((User) authentication.getPrincipal()).getId();
        return cartService.addItem(userId, createCartItemRequestDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    @Operation(summary = "Update Cart", description = "update cart by id")
    public CartResponseDto update(
            Authentication authentication,
            @PathVariable Long id,
            @Valid @RequestBody UpdateCartItemRequestDto updateCartItemRequestDto
    ) {
        Long userId = ((User) authentication.getPrincipal()).getId();
        return cartService.update(userId, id, updateCartItemRequestDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @Operation(summary = "Get Cart", description = "get all cart items for current user")
    public CartResponseDto getAll(
            Authentication authentication,
            Pageable pageable
    ) {
        Long userId = ((User) authentication.getPrincipal()).getId();
        return cartService.getAll(userId, pageable);
    }
}
