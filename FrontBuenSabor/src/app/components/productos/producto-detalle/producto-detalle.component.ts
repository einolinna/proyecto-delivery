import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MercadoPagoService } from 'src/app/services/mercado-pago.service';
import { Producto } from 'src/app/models/producto';
import { ProductosService } from 'src/app/services/productos.service';
import { CarritoService } from 'src/app/services/carrito.service';
declare var MercadoPago;

@Component({
  selector: 'app-producto-detalle',
  templateUrl: './producto-detalle.component.html',
  styleUrls: ['./producto-detalle.component.css'],
})
export class ProductoDetalleComponent implements OnInit {
  producto: Producto;
  cantidad: number;
  preferenceId: string;
  constructor(
    private activatedRoute: ActivatedRoute,
    private service: ProductosService,
    private _cartService: CarritoService,
    private serviceMP: MercadoPagoService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      this.producto = new Producto();
      console.log(params);
      if (params['insumo'] == 'true') {
        this.service.getArtInsumoById(params['id']).subscribe((data) => {
          console.log(data);
          this.producto.id = data.id;
          this.producto.denominacion = data.denominacion;
          this.producto.precioVenta = data.precioVenta;
          this.producto.imagen = '';
          this.producto.esInsumo = true;
          console.log(this.producto);
        });
      } else {
        this.service.getArtMfactById(params['id']).subscribe((data) => {
          console.log(data);
          this.producto.id = data.id;
          this.producto.denominacion = data.denominacion;
          this.producto.precioVenta = data.precioVenta;
          this.producto.imagen = '';
          this.producto.esInsumo = false;
          console.log(this.producto);
        });
      }
    });
  }
  comprar(prod: Producto) {
    this._cartService.changeCart(prod);
  }
}
