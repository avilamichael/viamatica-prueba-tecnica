package com.viamatica.prueba.menus.infrastructure;

import com.viamatica.prueba.menus.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("SELECT m FROM Menu m WHERE m.activo = true")
    List<Menu> findAllTopLevelMenus(); // Encuentra solo los menús de nivel superiors
}