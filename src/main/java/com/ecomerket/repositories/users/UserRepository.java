package com.ecomerket.repositories.users;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.ecomerket.models.users.User;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    //
    boolean existsByEmail(String email);

    boolean existsByRut(String rut);
}