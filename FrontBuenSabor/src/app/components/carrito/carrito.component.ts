import { Component, OnInit } from '@angular/core';
import { Producto } from 'src/app/models/producto';
import { CarritoService } from 'src/app/services/carrito.service';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css'],
})
export class CarritoComponent implements OnInit {
  public productos: Array<Producto>;
  public precioTotal: number = 0;
  public cantidadTotal: number = 0;

  constructor(private _cartService: CarritoService) {}

  ngOnInit(): void {
    this._cartService.currentDataCart$.subscribe((data) => {
      if (data) {
        this.productos = data;
        this.cantidadTotal = data.length;
        this.precioTotal = data.reduce(
          (sum, current) => sum + current.precioVenta * current.cantidad,
          0
        );
      }
    });
  }

  public remove(producto: Producto) {
    this._cartService.removeEmenetCart(producto);
  }
}
