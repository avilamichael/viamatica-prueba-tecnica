package com.viamatica.prueba.menus.application;

import com.viamatica.prueba.menus.domain.Menu;
import com.viamatica.prueba.menus.infrastructure.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> obtenerTodosLosMenus() {
        return menuRepository.findAll();
    }

    @Override
    public Menu guardarMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu actualizarMenu(Long id, Menu menuActualizado) {
        Optional<Menu> menuOptional = menuRepository.findById(id);
        if (menuOptional.isPresent()) {
            Menu menuExistente = menuOptional.get();
            menuExistente.setNombre(menuActualizado.getNombre());
            menuExistente.setDescripcion(menuActualizado.getDescripcion());
            menuExistente.setUrl(menuActualizado.getUrl());
            menuExistente.setIcono(menuActualizado.getIcono());
            menuExistente.setActivo(menuActualizado.getActivo());
            return menuRepository.save(menuExistente);
        }
        throw new RuntimeException("Menu con ID " + id + " no encontrado.");
    }

    @Override
    public void eliminarMenu(Long id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
        } else {
            throw new RuntimeException("Menu con ID " + id + " no encontrado.");
        }
    }

    @Override
    public List<Menu> obtenerEstructuraDeMenus() {
        return menuRepository.findAllTopLevelMenus(); // Solo devuelve los menús de nivel superior con sus hijos
    }
}