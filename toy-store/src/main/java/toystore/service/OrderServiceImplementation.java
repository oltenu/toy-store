package toystore.service;

import org.springframework.stereotype.Service;
import toystore.data.OrderRepository;
import toystore.data.ToyRepository;
import toystore.model.Order;
import toystore.model.Toy;

@Service
public class OrderServiceImplementation implements OrderService{

    private final OrderRepository orderRepository;
    private final ToyRepository toyRepository;

    public OrderServiceImplementation(OrderRepository orderRepository, ToyRepository toyRepository){
        this.orderRepository = orderRepository;
        this.toyRepository = toyRepository;
    }

    @Override
    public boolean placeOrder(Order order) {
        Toy toy = order.getToy();

        if(order.getQuantity() > toy.getQuantity()){
            return false;
        }

        toy.setQuantity(toy.getQuantity() - order.getQuantity());
        toyRepository.save(toy);

        orderRepository.save(order);

        return true;
    }
}
