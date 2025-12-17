package com.ecomerket.models.dtos;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderRequestDTO {
    private List<ItemDTO> items;
}