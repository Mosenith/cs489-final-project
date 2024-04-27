package food.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Order_History")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long orderId;
    private Long userId;

    @ElementCollection
    @CollectionTable(name = "Order_History_Menu_Names",
            joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "menu_name")
    private List<String> menuNames = new ArrayList<>();

    private Double totalPrice;

    @NotEmpty(message = "Name is required")
    @Size(min = 3, max = 50)
    private String name;

    @NotEmpty(message = "Street is required")
    @Size(min = 3, max = 100)
    private String street;

    @NotEmpty(message = "City is required")
    @Size(min = 3, max = 50)
    private String city;

    @NotEmpty(message = "State is required")
    @Size(min = 2, max = 10)
    private String state;

    @NotEmpty(message = "Zip is required")
    @Size(min = 5, max = 10)
    private String zip;

    //    @CreditCardNumber(message = "Not a valid credit card number")
    @Transient
    private String ccNumber;


    @Transient
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Transient
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private String placedAt;

    @PrePersist
    void placedAt() {
        this.placedAt = "27-04-2024";
    }

}
