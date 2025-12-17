package com.ecomerket.repositories.users;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.ecomerket.models.users.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}