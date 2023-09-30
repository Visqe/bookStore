package mate.academy.bookstore.service;

import java.util.Set;
import mate.academy.bookstore.dto.user.UserRegistrationRequest;
import mate.academy.bookstore.dto.user.UserResponseDto;
import mate.academy.bookstore.exception.RegistrationExeption;
import mate.academy.bookstore.model.Role;

public interface UserService {
    UserResponseDto register(UserRegistrationRequest request, Set<Role> roles)
            throws RegistrationExeption;
}
