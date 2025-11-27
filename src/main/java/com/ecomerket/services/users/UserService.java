package com.ecomerket.services.users;
import com.ecomerket.models.users.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    User registerUser(User user);

    void deleteUser(Long user);
}