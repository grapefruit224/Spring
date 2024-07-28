package com.example.spring_weekly.DTO;

import com.example.spring_weekly.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {

    private Integer id;
    private String name;
    private String address;
    private String phone;

    // Entity를 DTO로 변환하는 정적 메서드
    public static CustomerDTO fromEntity(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .build();
    }

    public Customer toEntity() {
        return new Customer(
                this.id,
                this.name,
                this.address,
                this.phone
        );
    }
}
