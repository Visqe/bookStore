package mate.academy.bookstore.service.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.user.UserRegistrationRequest;
import mate.academy.bookstore.dto.user.UserResponseDto;
import mate.academy.bookstore.exception.RegistrationExeption;
import mate.academy.bookstore.mapper.UserMapper;
import mate.academy.bookstore.model.Cart;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.CartRepository;
import mate.academy.bookstore.repository.RoleRepository;
import mate.academy.bookstore.repository.UserRepository;
import mate.academy.bookstore.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final CartRepository cartRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequest request)
            throws RegistrationExeption {
        User newUser = createUserFromRequest(request);
        newUser.setRoles(Set.of(roleRepository.findByName("ROLE_USER")));
        User savedUser = userRepository.save(newUser);
        Cart cart = new Cart();
        cart.setUser(savedUser);
        cartRepository.save(cart);
        return userMapper.toUserResponseDto(savedUser);
    }

    @Override
    public UserResponseDto registerAdmin(UserRegistrationRequest request)
            throws RegistrationExeption {
        User newUser = createUserFromRequest(request);
        newUser.setRoles(Set.of(roleRepository.findByName("ROLE_ADMIN")));
        User savedUser = userRepository.save(newUser);
        return userMapper.toUserResponseDto(savedUser);
    }

    private User createUserFromRequest(UserRegistrationRequest request)
            throws RegistrationExeption {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrationExeption(
                    "Unable to complete registration, user with this email exist already"
            );
        }
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setShippingAddress(request.getShippingAddress());
        return newUser;
    }
}
