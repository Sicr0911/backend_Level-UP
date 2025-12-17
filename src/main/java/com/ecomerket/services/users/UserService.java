package com.ecomerket.services.users;
import java.util.List;
import java.util.Optional;
import com.ecomerket.models.users.User;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    boolean existsByUsername(String username);
    Optional<User> update(Long id, User user);
    void delete(Long id);
}