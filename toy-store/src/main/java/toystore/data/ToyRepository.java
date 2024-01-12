package toystore.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import toystore.model.Toy;

@Repository
public interface ToyRepository extends CrudRepository<Toy, Long> {
}
