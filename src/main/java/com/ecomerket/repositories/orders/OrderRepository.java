package com.ecomerket.repositories.orders;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.ecomerket.models.orders.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUserUsername(String username);
    List<Order> findAllByOrderByCreatedAtDesc();
}