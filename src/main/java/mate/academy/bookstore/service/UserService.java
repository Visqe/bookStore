package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.user.UserRegistrationRequest;
import mate.academy.bookstore.dto.user.UserResponseDto;
import mate.academy.bookstore.exception.RegistrationExeption;

public interface UserService {
    UserResponseDto register(UserRegistrationRequest userRegistrationRequest)
            throws RegistrationExeption;

    UserResponseDto registerAdmin(UserRegistrationRequest userRegistrationRequest)
            throws RegistrationExeption;
}
