package com.example.spring_weekly.Service;

import com.example.spring_weekly.DTO.*;
import com.example.spring_weekly.Repository.*;
import com.example.spring_weekly.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll().stream()
                .map(StoreDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public StoreDTO getStoreById(Long id) {
        return storeRepository.findById(id)
                .map(StoreDTO::fromEntity)
                .orElse(null);
    }

    @Transactional
    public StoreDTO createStore(StoreDTO storeDTO) {
        Store store = storeDTO.toEntity();
        return StoreDTO.fromEntity(storeRepository.save(store));
    }

    @Transactional
    public StoreDTO updateStore(Long id, StoreDTO storeDTO) {
        Optional<Store> storeOpt = storeRepository.findById(id);
        if (storeOpt.isPresent()) {
            Store store = storeOpt.get();
            store.setName(storeDTO.getName());
            store.setAddress(storeDTO.getAddress());
            store.setPhone(storeDTO.getPhone());
            return StoreDTO.fromEntity(storeRepository.save(store));
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }

    public List<Object[]> getStoreSalesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return ordersRepository.findStoreSalesBetweenDates(startDate, endDate);
    }
}
