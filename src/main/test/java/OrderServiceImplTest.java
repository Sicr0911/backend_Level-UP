import com.ecomerket.models.dtos.ItemDTO;
import com.ecomerket.models.dtos.OrderRequestDTO;
import com.ecomerket.models.orders.Order;
import com.ecomerket.models.products.Product;
import com.ecomerket.models.users.User;
import com.ecomerket.repositories.orders.OrderRepository;
import com.ecomerket.repositories.users.UserRepository;
import com.ecomerket.services.Orders.OrderServiceImpl;
import com.ecomerket.services.Products.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private OrderServiceImpl orderService;

    private User mockUser;
    private Product mockProduct;

    @BeforeEach
    void setUp() {
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("testuser");

        mockProduct = new Product();
        mockProduct.setId(100L);
        mockProduct.setName("Producto Test");
        mockProduct.setPrecio(1000);
        mockProduct.setStock(10);
    }

    @Test
    void createOrder_DebeCalcularTotalCorrectamente_SinDescuento() {
        OrderRequestDTO request = new OrderRequestDTO();
        request.setClientId(1L);
        request.setDireccionEnvio("Calle Falsa 123");
        request.setAplicaDescuentoDuoc(false);

        ItemDTO item = new ItemDTO();
        item.setProductId(100L);
        item.setCantidad(2);
        request.setItems(Collections.singletonList(item));

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(productService.findById(100L)).thenReturn(Optional.of(mockProduct));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order result = orderService.createOrder(request);

        assertNotNull(result);
        assertEquals(2000.0, result.getTotal());
        assertEquals(8, mockProduct.getStock());
    }

    @Test
    void createOrder_DebeAplicarDescuentoDuoc() {
        OrderRequestDTO request = new OrderRequestDTO();
        request.setClientId(1L);
        request.setDireccionEnvio("Duoc UC");
        request.setAplicaDescuentoDuoc(true);

        ItemDTO item = new ItemDTO();
        item.setProductId(100L);
        item.setCantidad(1);
        request.setItems(Collections.singletonList(item));

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(productService.findById(100L)).thenReturn(Optional.of(mockProduct));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order result = orderService.createOrder(request);

        assertEquals(800.0, result.getTotal());
        assertTrue(result.getAplicaDescuentoDuoc());
    }

    @Test
    void createOrder_DebeLanzarExcepcion_SiStockInsuficiente() {
        OrderRequestDTO request = new OrderRequestDTO();
        request.setClientId(1L);
        request.setAplicaDescuentoDuoc(false);

        ItemDTO item = new ItemDTO();
        item.setProductId(100L);
        item.setCantidad(20);
        request.setItems(Collections.singletonList(item));

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(productService.findById(100L)).thenReturn(Optional.of(mockProduct));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.createOrder(request);
        });

        assertTrue(exception.getMessage().contains("Stock insuficiente"));
        verify(orderRepository, never()).save(any());
    }

    @Test
    void createOrder_DebeLanzarExcepcion_SiClienteNoExiste() {
        OrderRequestDTO request = new OrderRequestDTO();
        request.setClientId(999L);

        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            orderService.createOrder(request);
        });
    }
}