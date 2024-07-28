package com.example.spring_weekly.Controller;

import com.example.spring_weekly.DTO.MenuDTO;
import com.example.spring_weekly.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<MenuDTO> getAllMenus(@RequestParam(required = false) String category) {
        if (category == null) {
            return menuService.getAllMenus();
        } else {
            return menuService.getMenusByCategory(category);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> getMenuById(@PathVariable Long id) {
        MenuDTO menu = menuService.getMenuById(id);
        if (menu != null) {
            return ResponseEntity.ok(menu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public MenuDTO createMenu(@RequestBody MenuDTO menuDTO) {
        return menuService.createMenu(menuDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuDTO> updateMenu(@PathVariable Long id, @RequestBody MenuDTO menuDTO) {
        MenuDTO updatedMenu = menuService.updateMenu(id, menuDTO);
        if (updatedMenu != null) {
            return ResponseEntity.ok(updatedMenu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top3")
    public List<MenuDTO> getTop3Menus() {
        return menuService.getTop3Menus();
    }

    @GetMapping("/category/{category}")
    public List<MenuDTO> getMenusByCategory(@PathVariable String category) {
        return menuService.getMenusByCategory(category);
    }
}
