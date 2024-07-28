package com.example.spring_weekly.Repository;

import com.example.spring_weekly.DTO.StoreDTO;
import com.example.spring_weekly.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCustomerId(Long customerId);
    List<Orders> findByStoreId(Long storeId);
    List<Orders> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT o.store.id, SUM(o.totalAmount) " +
            "FROM Orders o WHERE o.orderDate BETWEEN :startDate AND :endDate " +
            "GROUP BY o.store.id")
    List<Object[]> findStoreSalesBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
}

