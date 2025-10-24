import { Component, OnInit } from '@angular/core';
import { PrimeNG } from 'primeng/config';
import {MenuItem, MenuService} from './menu/service';
import {Menubar} from 'primeng/menubar';
import {Badge} from 'primeng/badge';
import {NgClass} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Menu} from "./menu/menu";

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
    imports: [
        Menubar,
        FormsModule,
        Menu
    ],
  styleUrl: './app.css'
})

export class App implements OnInit {
  items: MenuItem[] | undefined; // Menú inicial vacío

  constructor(private primeng: PrimeNG, private menuService: MenuService) {}

  ngOnInit() {
    this.primeng.ripple.set(true);
    this.loadMenu();
  }

  private loadMenu() {
    // Llama al servicio para obtener los menús y los mapea
    this.menuService.getMenuItems().subscribe((menus) => {
      this.items = menus;
    });
  }
}
