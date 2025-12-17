package com.ecomerket.repositories.users;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.ecomerket.models.users.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByRut(String rut);
}