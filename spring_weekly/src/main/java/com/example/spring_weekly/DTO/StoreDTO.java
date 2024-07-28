package com.example.spring_weekly.DTO;

import com.example.spring_weekly.entity.Store;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StoreDTO {

    private Integer id;
    private String name;
    private String address;
    private String phone;

    public static StoreDTO fromEntity(Store store) {
        return StoreDTO.builder()
                .id(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .phone(store.getPhone())
                .build();
    }

    public Store toEntity() {
        Store store = new Store();
        store.setId(this.getId());
        store.setName(this.getName());
        store.setAddress(this.getAddress());
        store.setPhone(this.getPhone());
        return store;
    }
}

