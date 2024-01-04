package toystore.service;

import org.springframework.stereotype.Service;
import toystore.model.Order;

public interface OrderService {
    boolean placeOrder(Order order);
}
