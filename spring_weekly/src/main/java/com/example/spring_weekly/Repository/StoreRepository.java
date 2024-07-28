package com.example.spring_weekly.Repository;

import com.example.spring_weekly.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByName(String name);

    @Query("SELECT s FROM Store s WHERE s.address LIKE %:address%")
    List<Store> findByAddressContaining(@Param("address") String address);
}

