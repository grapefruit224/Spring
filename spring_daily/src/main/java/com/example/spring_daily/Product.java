package com.example.spring_daily;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private double price;

    // 생성자, getter, setter 메서드 생략
}
