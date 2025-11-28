package com.ecomerket.services.users;

import com.ecomerket.models.users.Role;
import com.ecomerket.models.users.User;
import com.ecomerket.repositories.users.RoleRepository;
import com.ecomerket.repositories.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void registerUser_DebeEncriptarPasswordYAsignarRolUsuario() {
        // ARRANGE
        User usuarioNuevo = new User();
        usuarioNuevo.setUsername("gamer123");
        usuarioNuevo.setPassword("miPasswordSecreto");
        usuarioNuevo.setAdmin(false);

        Role roleUser = new Role("ROLE_USER");

        when(passwordEncoder.encode("miPasswordSecreto")).thenReturn("ENCRYPTED_HASH_123");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(roleUser));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        // ACT
        User resultado = userService.registerUser(usuarioNuevo);

        // ASSERT
        assertNotNull(resultado);
        // 1. Verificamos que la contraseña YA NO ES "miPasswordSecreto"
        assertEquals("ENCRYPTED_HASH_123", resultado.getPassword());
        // 2. Verificamos que se le asignó el rol
        assertFalse(resultado.getRoles().isEmpty());
        assertEquals("ROLE_USER", resultado.getRoles().get(0).getName());

        // Verificamos que se llamó al encoder
        verify(passwordEncoder, times(1)).encode(anyString());
    }
}