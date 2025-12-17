package com.ecomerket.models.dtos;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDTO {
    private Long productId;
    private Integer quantity;
    private Integer price;
}