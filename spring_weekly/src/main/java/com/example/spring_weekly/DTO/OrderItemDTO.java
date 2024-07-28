package com.example.spring_weekly.DTO;

import com.example.spring_weekly.entity.Menu;
import com.example.spring_weekly.entity.OrderItem;
import com.example.spring_weekly.entity.Orders;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderItemDTO {

    private Integer id;
    private Integer orderId;
    private Integer menuId;
    private int quantity;
    private BigDecimal price;
    private BigDecimal subtotal;

    public static OrderItemDTO fromEntity(OrderItem item) {
        return OrderItemDTO.builder()
                .id(item.getId())
                .orderId(item.getOrder().getId())
                .menuId(item.getMenu().getId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .subtotal(item.getSubtotal())
                .build();
    }

    public OrderItem toEntity() {
        OrderItem item = new OrderItem();
        item.setId(this.getId());
        item.setOrder(new Orders());
        item.getOrder().setId(this.getOrderId());
        item.setMenu(new Menu());
        item.getMenu().setId(this.getMenuId());
        item.setQuantity(this.getQuantity());
        item.setPrice(this.getPrice());
        item.setSubtotal(this.getSubtotal());
        return item;
    }
}

