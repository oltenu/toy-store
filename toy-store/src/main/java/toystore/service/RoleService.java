package toystore.service;

import org.springframework.stereotype.Service;
import toystore.model.Role;

import java.util.List;

public interface RoleService {
    Role findByName(String name);

    List<Role> findAllRoles();
}
