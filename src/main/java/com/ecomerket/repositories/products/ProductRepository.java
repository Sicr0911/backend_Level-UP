package com.ecomerket.repositories.products;

import org.springframework.data.repository.CrudRepository;
import com.ecomerket.models.products.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}