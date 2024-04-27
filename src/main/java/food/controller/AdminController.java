package food.controller;

import food.service.CustomUserService;
import food.service.OrderService;
import food.service.dto.OrderDto;
import food.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CustomUserService userService;

    @Autowired
    OrderService orderService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/orderRecords")
    public ResponseEntity<?> getOrderRecords() {
        List<OrderDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
