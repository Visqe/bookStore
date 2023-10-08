package mate.academy.bookstore.controller;

import jakarta.validation.Valid;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.user.UserLoginRequestDto;
import mate.academy.bookstore.dto.user.UserLoginResponseDto;
import mate.academy.bookstore.dto.user.UserRegistrationRequest;
import mate.academy.bookstore.dto.user.UserResponseDto;
import mate.academy.bookstore.exception.RegistrationExeption;
import mate.academy.bookstore.model.Role;
import mate.academy.bookstore.repository.RoleRepository;
import mate.academy.bookstore.security.AuthenticationService;
import mate.academy.bookstore.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RoleRepository roleRepository;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequest request)
            throws RegistrationExeption {
        Set<Role> roles = Set.of(roleRepository.findByName(Role.RoleName.ROLE_USER));
        return userService.register(request, roles);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/registeradmin")
    public UserResponseDto registerAdmin(@RequestBody @Valid UserRegistrationRequest request)
            throws RegistrationExeption {
        Set<Role> roles = Set.of(roleRepository.findByName(Role.RoleName.ROLE_ADMIN));
        return userService.register(request, roles);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
