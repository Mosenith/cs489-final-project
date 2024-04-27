package food.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String role;

    private String token;

    public UserDto(Long id, String username, String password, String fullName, String street, String city, String state, String zip, String phoneNumber, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}
