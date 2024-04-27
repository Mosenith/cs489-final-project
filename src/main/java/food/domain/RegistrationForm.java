package food.domain;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String role;
    // Create User Object for processRegistration() to save
    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(null,username, passwordEncoder.encode(password),
                fullName, street, city, state, zip, phone, role);

    }
}
