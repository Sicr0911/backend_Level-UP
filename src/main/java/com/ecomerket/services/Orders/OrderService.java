package com.ecomerket.services.Orders;
import java.util.List;
import com.ecomerket.models.dtos.OrderRequestDTO;
import com.ecomerket.models.orders.Order;

public interface OrderService {
    Order save(OrderRequestDTO orderRequest, String username);
    List<Order> findAll();
    List<Order> findByUser(String username);
}