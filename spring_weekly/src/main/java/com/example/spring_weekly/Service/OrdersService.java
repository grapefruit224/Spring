package com.example.spring_weekly.Service;

import com.example.spring_weekly.Repository.*;
import com.example.spring_weekly.DTO.*;
import com.example.spring_weekly.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private MenuRepository menuRepository;

    public List<OrdersDTO> getAllOrders() {
        return ordersRepository.findAll().stream()
                .map(OrdersDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public OrdersDTO getOrderById(Long id) {
        return ordersRepository.findById(id)
                .map(OrdersDTO::fromEntity)
                .orElse(null);
    }

    @Transactional
    public OrdersDTO createOrder(OrdersDTO ordersDTO) {
        Orders order = ordersDTO.toEntity();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("접수");

        Orders savedOrder = ordersRepository.save(order);

        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderItemDTO itemDTO : ordersDTO.getOrderItems()) {
            Menu menu = menuRepository.findById(itemDTO.getMenuId()).orElseThrow(() -> new RuntimeException("Menu not found"));
            OrderItem item = new OrderItem();
            item.setOrder(savedOrder);
            item.setMenu(menu);
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(menu.getPrice());
            item.setSubtotal(menu.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity())));
            totalAmount = totalAmount.add(item.getSubtotal());
            orderItemRepository.save(item);
        }

        savedOrder.setTotalAmount(totalAmount);
        return OrdersDTO.fromEntity(ordersRepository.save(savedOrder));
    }

    @Transactional
    public OrdersDTO updateOrder(Long id, OrdersDTO ordersDTO) {
        Optional<Orders> orderOpt = ordersRepository.findById(id);
        if (orderOpt.isPresent()) {
            Orders order = orderOpt.get();
            order.setStatus(ordersDTO.getStatus());
            return OrdersDTO.fromEntity(ordersRepository.save(order));
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteOrder(Long id) {
        ordersRepository.deleteById(id);
    }

    public List<OrdersDTO> getOrdersByCustomer(Long customerId) {
        return ordersRepository.findByCustomerId(customerId).stream()
                .map(OrdersDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<OrdersDTO> getOrdersByStore(Long storeId) {
        return ordersRepository.findByStoreId(storeId).stream()
                .map(OrdersDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
