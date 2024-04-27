package food.service;

import food.domain.Order;
import food.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testCreateOrder() {
        // Create a sample order
        Order order = new Order();
        order.setUserId(1L); // Set user ID
        order.getMenuNames().add("Burger"); // Add a menu item
        order.setTotalPrice(10.0); // Set total price
        order.setName("John Doe"); // Set name
        order.setStreet("123 Main St"); // Set street
        order.setCity("Anytown"); // Set city
        order.setState("NY"); // Set state
        order.setZip("12345"); // Set zip

        // Mock behavior of orderRepository.save()
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // Call the createOrder method
        Order createdOrder = orderService.createOrder(order);

        // Verify that orderRepository.save() was called
        verify(orderRepository, times(1)).save(order);

        // Verify that the returned order is the same as the one passed to save
        assertEquals(order, createdOrder);
    }

    @Test
    public void testGetOrderById() {
        // Create a sample order
        Order order = new Order();
        order.setOrderId(1L); // Set order ID
        order.setUserId(1L); // Set user ID
        order.getMenuNames().add("Pizza"); // Add a menu item
        order.setTotalPrice(15.0); // Set total price
        order.setName("Jane Doe"); // Set name
        order.setStreet("456 Elm St"); // Set street
        order.setCity("Othertown"); // Set city
        order.setState("CA"); // Set state
        order.setZip("54321"); // Set zip

        // Mock behavior of orderRepository.findOrderByOrderId()
        when(orderRepository.findOrderByOrderId(1L)).thenReturn(order);

        // Call the getOrderById method
        Order retrievedOrder = orderService.getOrderById(1L);

        // Verify that orderRepository.findOrderByOrderId() was called
        verify(orderRepository, times(1)).findOrderByOrderId(1L);

        // Verify that the returned order is the same as the one mocked
        assertEquals(order, retrievedOrder);
    }
}
