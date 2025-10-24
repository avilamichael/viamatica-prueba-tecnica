package com.viamatica.prueba.menus.controller;

import com.viamatica.prueba.menus.application.MenuService;
import com.viamatica.prueba.menus.domain.Menu;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<Menu>> obtenerTodosLosMenus() {
        return ResponseEntity.ok(menuService.obtenerTodosLosMenus());
    }

    @GetMapping("/estructura")
    public ResponseEntity<List<Menu>> obtenerEstructuraDeMenus() {
        return ResponseEntity.ok(menuService.obtenerEstructuraDeMenus()); // Devuelve el árbol
    }

    @PostMapping
    public ResponseEntity<Menu> guardarMenu(@RequestBody Menu menu) {
        Menu nuevoMenu = menuService.guardarMenu(menu);
        return ResponseEntity.ok(nuevoMenu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> actualizarMenu(@PathVariable Long id, @RequestBody Menu menu) {
        Menu menuActualizado = menuService.actualizarMenu(id, menu);
        return ResponseEntity.ok(menuActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMenu(@PathVariable Long id) {
        menuService.eliminarMenu(id);
        return ResponseEntity.noContent().build();
    }
}
