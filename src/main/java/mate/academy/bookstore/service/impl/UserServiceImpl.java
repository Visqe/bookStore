package mate.academy.bookstore.service.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.user.UserRegistrationRequest;
import mate.academy.bookstore.dto.user.UserResponseDto;
import mate.academy.bookstore.exception.RegistrationExeption;
import mate.academy.bookstore.mapper.UserMapper;
import mate.academy.bookstore.model.Role;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.UserRepository;
import mate.academy.bookstore.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequest request, Set<Role> roles)
            throws RegistrationExeption {
        User newUser = createUserFromRequest(request);
        newUser.setRoles(roles);
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
