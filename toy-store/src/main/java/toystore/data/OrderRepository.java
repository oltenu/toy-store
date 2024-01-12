package toystore.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import toystore.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
