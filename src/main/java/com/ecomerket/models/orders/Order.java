package com.ecomerket.models.orders;
import java.util.Date;
import java.util.List;
import com.ecomerket.models.users.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="orders")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"roles", "password", "enabled", "handler", "hibernateLazyInitializer"})
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private Double total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    private List<OrderDetail> details;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }
}