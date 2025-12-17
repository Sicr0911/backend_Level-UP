package com.ecomerket.services.users;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecomerket.models.users.Role;
import com.ecomerket.models.users.User;
import com.ecomerket.repositories.users.RoleRepository;
import com.ecomerket.repositories.users.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        if (user.getPassword().length() < 4 || user.getPassword().length() > 10) {
            throw new IllegalArgumentException("La contraseña debe tener entre 4 y 10 caracteres");
        }

        String emailPattern = "^[\\w-\\.]+@(duoc\\.cl|profesor\\.duoc\\.cl|gmail\\.com)$";
        if (!Pattern.matches(emailPattern, user.getUsername())) {
            throw new IllegalArgumentException("El correo debe ser @duoc.cl, @profesor.duoc.cl o @gmail.com");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            List<Role> roles = new ArrayList<>();
            roleRepository.findByName("ROLE_CLIENTE").ifPresent(roles::add);
            user.setRoles(roles);
        }

        user.setEnabled(true);
        return repository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    @Transactional
    public Optional<User> update(Long id, User user) {
        Optional<User> userDb = repository.findById(id);
        if (userDb.isPresent()) {
            User existingUser = userDb.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setRut(user.getRut());
            return Optional.of(repository.save(existingUser));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}