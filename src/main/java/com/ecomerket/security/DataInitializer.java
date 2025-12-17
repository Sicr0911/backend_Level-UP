package com.ecomerket.security;
import com.ecomerket.models.users.Role;
import com.ecomerket.models.users.User;
import com.ecomerket.repositories.users.RoleRepository;
import com.ecomerket.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            roleRepository.save(new Role(null, "ROLE_ADMIN"));
        }

        if (roleRepository.findByName("ROLE_VENDEDOR").isEmpty()) {
            roleRepository.save(new Role(null, "ROLE_VENDEDOR"));
        }

        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            roleRepository.save(new Role(null, "ROLE_USER"));
        }

        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@duoc.cl");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setRut("11111111-1");
            admin.setEnabled(true);

            List<Role> roles = new ArrayList<>();
            roleRepository.findByName("ROLE_ADMIN").ifPresent(roles::add);
            admin.setRoles(roles);

            userRepository.save(admin);
        }
    }
}