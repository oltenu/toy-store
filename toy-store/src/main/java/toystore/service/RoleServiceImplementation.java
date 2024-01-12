package toystore.service;

import org.springframework.stereotype.Service;
import toystore.data.RoleRepository;
import toystore.model.Role;

import java.util.List;

@Service
public class RoleServiceImplementation implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImplementation(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
