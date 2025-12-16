package com.ecomerket.config;

import com.ecomerket.models.users.Role;
import com.ecomerket.models.users.User;
import com.ecomerket.repositories.users.RoleRepository;
import com.ecomerket.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println(">>> INICIANDO CARGA DE DATOS AUTOMÁTICA...");

        Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));
        Role userRole = roleRepository.findByName("ROLE_USER").orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));

        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEnabled(true);
            admin.setAdmin(true);
            admin.setEmail("admin@ecomerket.com");

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            roles.add(userRole);
            admin.setRoles(new ArrayList<>(roles));

            userRepository.save(admin);
            System.out.println(">>> USUARIO ADMIN CREADO: Username: 'admin' / Password: 'admin123'");
        } else {
            System.out.println(">>> USUARIO ADMIN YA EXISTE.");
        }
    }
}