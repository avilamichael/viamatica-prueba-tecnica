package com.viamatica.prueba.menus.application;

import com.viamatica.prueba.menus.domain.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> obtenerTodosLosMenus();

    Menu guardarMenu(Menu menu);

    Menu actualizarMenu(Long id, Menu menu);

    void eliminarMenu(Long id);

    List<Menu> obtenerEstructuraDeMenus();
}