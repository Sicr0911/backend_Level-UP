package com.ecomerket.services.users;
import com.ecomerket.models.users.Role;
import com.ecomerket.models.users.User;
import com.ecomerket.repositories.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JpaUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JpaUserDetailsService userDetailsService;

    @Test
    void loadUserByUsername_DebeRetornarUserDetails_CuandoUsuarioExiste() {
        User mockUser = new User();
        mockUser.setUsername("admin");
        mockUser.setPassword("12345");
        mockUser.setEnabled(true);
        Role adminRole = new Role("ROLE_ADMIN");
        mockUser.setRoles(Collections.singletonList(adminRole));

        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(mockUser));

        UserDetails result = userDetailsService.loadUserByUsername("admin");

        assertNotNull(result);
        assertEquals("admin", result.getUsername());
        assertTrue(result.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }

    @Test
    void loadUserByUsername_DebeLanzarExcepcion_CuandoUsuarioNoExiste() {
        when(userRepository.findByUsername("fantasma")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("fantasma");
        });
    }
}