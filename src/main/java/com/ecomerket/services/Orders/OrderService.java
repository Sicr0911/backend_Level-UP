package com.ecomerket.services.Orders;

import com.ecomerket.models.dtos.OrderRequestDTO;
import com.ecomerket.models.orders.Order;

import java.util.List;
public interface OrderService {

    List<Order> findAll();

    Order findById(Long id);

    Order createOrder(OrderRequestDTO orderDto);

    void deleteOrder(Long id);
}