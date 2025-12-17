package com.ecomerket.services.Orders;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecomerket.models.dtos.ItemDTO;
import com.ecomerket.models.dtos.OrderRequestDTO;
import com.ecomerket.models.orders.Order;
import com.ecomerket.models.orders.OrderDetail;
import com.ecomerket.models.products.Product;
import com.ecomerket.models.users.User;
import com.ecomerket.repositories.orders.OrderRepository;
import com.ecomerket.repositories.products.ProductRepository;
import com.ecomerket.repositories.users.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ProductRepository productRepository;

    @Override
    @Transactional
    public Order save(OrderRequestDTO orderRequest, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Order order = new Order();
        order.setUser(user);

        List<OrderDetail> details = new ArrayList<>();
        Double totalOrder = 0.0;

        for (ItemDTO item : orderRequest.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado ID: " + item.getProductId()));

            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para: " + product.getName());
            }

            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);

            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(product);
            detail.setQuantity(item.getQuantity());
            detail.setPrice(product.getPrecio());

            details.add(detail);
            totalOrder += (product.getPrecio() * item.getQuantity());
        }

        order.setDetails(details);
        order.setTotal(totalOrder);

        return orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findByUser(String username) {
        return orderRepository.findByUserUsername(username);
    }
}