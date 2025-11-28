package com.ecomerket.controllers.products;
import com.ecomerket.models.products.Product;
import com.ecomerket.services.Products.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void list_DebeRetornarListaDeProductosYStatus200() throws Exception {
        Product p1 = new Product(1L, "PS5", "Consolas", 500000, "Desc", "img.png", 10);
        Product p2 = new Product(2L, "Xbox", "Consolas", 400000, "Desc", "img.png", 5);
        List<Product> productos = Arrays.asList(p1, p2);

        given(productService.findAll()).willReturn(productos);

        mockMvc.perform(get("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("PS5"));
    }

    @Test
    void create_DebeRetornar201_CuandoProductoEsValido() throws Exception {
        Product nuevoProducto = new Product(null, "Nintendo Switch", "Consolas", 300000, "Híbrida", "img.png", 20);
        Product productoGuardado = new Product(1L, "Nintendo Switch", "Consolas", 300000, "Híbrida", "img.png", 20);

        when(productService.save(any(Product.class))).thenReturn(productoGuardado);

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevoProducto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void create_DebeRetornar400_CuandoFaltanDatosObligatorios() throws Exception {

        Product productoInvalido = new Product();
        productoInvalido.setPrecio(-100);

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productoInvalido)))
                .andExpect(status().isBadRequest());
    }
}