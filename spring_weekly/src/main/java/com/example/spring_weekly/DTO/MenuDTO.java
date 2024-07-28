package com.example.spring_weekly.DTO;

import com.example.spring_weekly.entity.Menu;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MenuDTO {

    private Integer id;
    private String name;
    private String category;
    private BigDecimal price;
    private String description;

    public static MenuDTO fromEntity(Menu menu) {
        return MenuDTO.builder()
                .id(menu.getId())
                .name(menu.getName())
                .category(menu.getCategory())
                .price(menu.getPrice())
                .description(menu.getDescription())
                .build();
    }

    public Menu toEntity() {
        Menu menu = new Menu();
        menu.setId(this.getId());
        menu.setName(this.getName());
        menu.setCategory(this.getCategory());
        menu.setPrice(this.getPrice());
        menu.setDescription(this.getDescription());
        return menu;
    }
}

