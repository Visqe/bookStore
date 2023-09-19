package mate.academy.bookstore.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mate.academy.bookstore.validation.FieldMatch;

@FieldMatch(
        password = "password",
        repeatPassword = "repeatPassword",
        message = "The password fields must match")
@Data
public class UserRegistrationRequest {
    @Email
    @NotBlank
    @Size(min = 4, max = 50)
    private String email;

    @NotBlank
    @Size(min = 6, max = 50)
    private String password;

    @NotBlank
    @Size(min = 6, max = 50)
    private String repeatPassword;

    @NotBlank
    @Size(min = 6, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 6, max = 50)
    private String lastName;

    private String shippingAddress;
}
