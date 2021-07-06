import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ArticuloInsumo } from 'src/app/models/ArticuloInsumo';
import { ArticuloMfact } from 'src/app/models/ArticuloMfact';
import { Producto } from 'src/app/models/producto';
import { ProductosService } from 'src/app/services/productos.service';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css'],
})
export class ProductosComponent implements OnInit {
  productos: Producto[] = [];
  articuloInsumo: ArticuloInsumo[] = [];
  articuloMfact: ArticuloMfact[] = [];
  constructor(private service: ProductosService, private router: Router) {}

  ngOnInit(): void {
    this.service.getLikeArtInsumo().subscribe((data) => {
      for (const art of data) {
        const prod = new Producto();
        prod.id = art.id;
        prod.denominacion = art.denominacion;
        prod.imagen = '';
        prod.precioVenta = art.precioVenta;
        prod.esInsumo = true;
        this.productos.push(prod);
      }
      this.service.getLikeArtMfact().subscribe((data1) => {
        for (const art of data1) {
          const prod = new Producto();
          prod.id = art.id;
          prod.denominacion = art.denominacion;
          prod.imagen = '';
          prod.precioVenta = art.precioVenta;
          prod.esInsumo = false;
          this.productos.push(prod);
        }
      });
    });
  }
}
