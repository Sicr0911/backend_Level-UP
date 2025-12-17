package com.ecomerket.services.Products;
import java.util.List;
import java.util.Optional;
import com.ecomerket.models.products.Product;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Optional<Product> update(Long id, Product product);
    Optional<Product> delete(Long id);
}