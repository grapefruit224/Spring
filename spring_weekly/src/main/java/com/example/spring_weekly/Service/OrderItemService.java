package com.example.spring_weekly.Service;

import com.example.spring_weekly.DTO.*;
import com.example.spring_weekly.Repository.*;
import com.example.spring_weekly.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItemDTO> getAllOrderItems() {
        return orderItemRepository.findAll().stream()
                .map(OrderItemDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public OrderItemDTO getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .map(OrderItemDTO::fromEntity)
                .orElse(null);
    }

    @Transactional
    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = orderItemDTO.toEntity();
        return OrderItemDTO.fromEntity(orderItemRepository.save(orderItem));
    }

    @Transactional
    public OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDTO) {
        Optional<OrderItem> orderItemOpt = orderItemRepository.findById(id);
        if (orderItemOpt.isPresent()) {
            OrderItem orderItem = orderItemOpt.get();
            orderItem.setQuantity(orderItemDTO.getQuantity());
            orderItem.setPrice(orderItemDTO.getPrice());
            orderItem.setSubtotal(orderItemDTO.getSubtotal());
            return OrderItemDTO.fromEntity(orderItemRepository.save(orderItem));
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

    public List<OrderItemDTO> getOrderItemsByOrder(Long orderId) {
        return orderItemRepository.findByOrderId(orderId).stream()
                .map(OrderItemDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<OrderItemDTO> getOrderItemsByMenu(Long menuId) {
        return orderItemRepository.findByMenuId(menuId).stream()
                .map(OrderItemDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
