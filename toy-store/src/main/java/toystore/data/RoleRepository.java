package toystore.data;

import org.springframework.data.jpa.repository.JpaRepository;
import toystore.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
