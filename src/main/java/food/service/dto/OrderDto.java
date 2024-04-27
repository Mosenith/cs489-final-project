package food.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private Long userId;

    private List<String> menuNames = new ArrayList<>();

    private Double totalPrice;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;

    public OrderDto(Long userId, List<String> menuNames, Double totalPrice, String name, String street, String city, String state, String zip) {
        this.userId = userId;
        this.menuNames = menuNames;
        this.totalPrice = totalPrice;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}
