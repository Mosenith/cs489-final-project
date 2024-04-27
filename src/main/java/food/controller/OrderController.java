package food.controller;

import food.domain.Order;
import food.service.CustomUserService;
import food.service.OrderService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@Controller
@CrossOrigin
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    CustomUserService userService;
    @Autowired
    HttpSession httpSession; // Inject HttpSession

    @GetMapping("/orders/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping("/orders")
    public String processOrder(@Valid Order order, Errors errors,
                               BindingResult bindingResult) {
        if(errors.hasErrors()) {
            return "orderForm";
        }

        if (bindingResult.hasErrors()) {
            return "orderForm";
        }

        // Retrieve session attributes
        String token = (String) httpSession.getAttribute("token");
        String checkedItems = (String) httpSession.getAttribute("checkedItems");
        Double totalPrice = (Double) httpSession.getAttribute("totalPrice");

        order.setUserId(userService.getUserByToken(token).getId());
        order.setMenuNames(Arrays.asList(checkedItems.split(",")));
        order.setTotalPrice(totalPrice);

        orderService.createOrder(order);

        log.info("Passed curOrder: {}", order);

        return "thankYou";
    }

//    @GetMapping("/test/{orderId}")
//    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
//        OrderOldVersion searchOrderOldVersion = orderService.getOrderById(orderId);
//        if(searchOrderOldVersion == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(searchOrderOldVersion, HttpStatus.OK);
//    }
//
//    @PostMapping("/test")
//    public ResponseEntity<?> addOrder(@RequestBody OrderOldVersion orderOldVersion) {
//        OrderOldVersion createdOrderOldVersion = orderService.createOrder(orderOldVersion);
//        return new ResponseEntity<>(createdOrderOldVersion, HttpStatus.OK);
//    }
}
