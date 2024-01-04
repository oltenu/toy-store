package toystore.service;

import toystore.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    void updateUser(User user);

    User findUserByEmail(String email);

    List<User> findAllUsers();

    User findById(Long id);

    void deleteUser(Long id);
}
