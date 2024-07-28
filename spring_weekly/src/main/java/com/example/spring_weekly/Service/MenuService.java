package com.example.spring_weekly.Service;

import com.example.spring_weekly.DTO.MenuDTO;
import com.example.spring_weekly.Repository.MenuRepository;
import com.example.spring_weekly.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<MenuDTO> getAllMenus() {
        return menuRepository.findAll().stream()
                .map(MenuDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<MenuDTO> getMenusByCategory(String category) {
        return menuRepository.findByCategory(category).stream()
                .map(MenuDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public MenuDTO getMenuById(Long id) {
        return menuRepository.findById(id)
                .map(MenuDTO::fromEntity)
                .orElse(null);
    }

    @Transactional
    public MenuDTO createMenu(MenuDTO menuDTO) {
        Menu menu = menuDTO.toEntity();
        return MenuDTO.fromEntity(menuRepository.save(menu));
    }

    @Transactional
    public MenuDTO updateMenu(Long id, MenuDTO menuDTO) {
        Optional<Menu> menuOpt = menuRepository.findById(id);
        if (menuOpt.isPresent()) {
            Menu menu = menuOpt.get();
            menu.setName(menuDTO.getName());
            menu.setCategory(menuDTO.getCategory());
            menu.setPrice(menuDTO.getPrice());
            menu.setDescription(menuDTO.getDescription());
            return MenuDTO.fromEntity(menuRepository.save(menu));
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    public List<MenuDTO> getTop3Menus() {
        return menuRepository.findTop3ByOrderCount().stream()
                .map(MenuDTO::fromEntity)
                .collect(Collectors.toList());
    }
}

