package com.example.spring_weekly.DTO;

import com.example.spring_weekly.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersDTO {

    private Integer id;
    private Integer customerId;
    private Integer storeId;
    private String status;
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private List<OrderItemDTO> orderItems;

    public static OrdersDTO fromEntity(Orders order) {
        return OrdersDTO.builder()
                .id(order.getId())
                .customerId(order.getCustomer().getId())
                .storeId(order.getStore().getId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .orderDate(order.getOrderDate())
                .build();
    }

    public Orders toEntity() {
        Orders order = new Orders();
        order.setId(this.getId());
        order.setCustomer(new Customer());
        order.getCustomer().setId(this.getCustomerId());
        order.setStore(new Store());
        order.getStore().setId(this.getStoreId());
        order.setStatus(this.getStatus());
        order.setTotalAmount(this.getTotalAmount());
        order.setOrderDate(this.getOrderDate());
        return order;
    }

    // Getters and Setters
}

