package food.repository;

import food.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order save(Order order);

    Order findOrderByUserId(Long id);

    Order findOrderByOrderId(Long id);
}
