package com.ecomerket.services.Products;
import com.ecomerket.models.products.Product;
import com.ecomerket.repositories.products.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void findAll_DebeRetornarListaDeProductos() {
        Product p1 = new Product(); p1.setName("P1");
        Product p2 = new Product(); p2.setName("P2");
        when(productRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Product> resultado = productService.findAll();

        assertEquals(2, resultado.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById_DebeRetornarProducto_SiExiste() {
        Product p = new Product();
        p.setId(1L);
        p.setName("Producto Test");
        when(productRepository.findById(1L)).thenReturn(Optional.of(p));

        Product resultado = productService.getProductById(1L);

        assertNotNull(resultado);
        assertEquals("Producto Test", resultado.getName());
    }

    @Test
    void getProductById_DebeLanzarExcepcion_SiNoExiste() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            productService.getProductById(99L);
        });
        assertEquals("Producto con ID 99 no encontrado.", ex.getMessage());
    }
}