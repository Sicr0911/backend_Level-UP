package com.ecomerket.controllers.orders;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.ecomerket.models.dtos.OrderRequestDTO;
import com.ecomerket.models.orders.Order;
import com.ecomerket.services.Orders.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(originPatterns = "*")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderRequestDTO orderRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(orderRequest, username));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/all")
    public List<Order> listAll() {
        return service.findAll();
    }

    @GetMapping("/my-orders")
    public List<Order> listMine() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return service.findByUser(auth.getName());
    }
}