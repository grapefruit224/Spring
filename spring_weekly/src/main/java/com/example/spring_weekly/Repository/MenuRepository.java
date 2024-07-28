package com.example.spring_weekly.Repository;

import com.example.spring_weekly.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByCategory(String category);
    Optional<Menu> findById(Integer id);
    @Query("SELECT m FROM Menu m ORDER BY (SELECT COUNT(oi) FROM OrderItem oi WHERE oi.menu.id = m.id) DESC")
    List<Menu> findTop3ByOrderCount();
}

