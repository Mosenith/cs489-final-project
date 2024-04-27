package food.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Menu {
    @Id
    private String id;

    @NotEmpty(message = "Name may not be empty")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 50 characters long")
    private String name;

    @NotBlank(message = "Name may not be blank")
    @Size(min = 2, max = 30, message = "Type must be between 2 and 30 characters long")
    private Type type;

    public static enum Type {
        FAST_FOOD, ASIAN, JAPANESE, WESTERN, FRENCH, ITALIAN
    }
}
