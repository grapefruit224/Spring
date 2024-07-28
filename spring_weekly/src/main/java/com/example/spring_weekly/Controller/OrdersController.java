package com.example.spring_weekly.Controller;

import com.example.spring_weekly.DTO.OrdersDTO;
import com.example.spring_weekly.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping
    public List<OrdersDTO> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrderById(@PathVariable Long id) {
        OrdersDTO order = ordersService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public OrdersDTO createOrder(@RequestBody OrdersDTO ordersDTO) {
        return ordersService.createOrder(ordersDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdersDTO> updateOrder(@PathVariable Long id, @RequestBody OrdersDTO ordersDTO) {
        OrdersDTO updatedOrder = ordersService.updateOrder(id, ordersDTO);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{customerId}")
    public List<OrdersDTO> getOrdersByCustomer(@PathVariable Long customerId) {
        return ordersService.getOrdersByCustomer(customerId);
    }

    @GetMapping("/store/{storeId}")
    public List<OrdersDTO> getOrdersByStore(@PathVariable Long storeId) {
        return ordersService.getOrdersByStore(storeId);
    }
}

