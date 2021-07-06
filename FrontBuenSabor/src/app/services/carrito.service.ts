import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Producto } from '../models/producto';

@Injectable({
  providedIn: 'root',
})
export class CarritoService {
  private cart = new BehaviorSubject<Array<Producto>>(null);
  public currentDataCart$ = this.cart.asObservable();

  constructor() {}

  public changeCart(newData: Producto) {
    let listCart = this.cart.getValue();
    if (listCart) {
      let objIndex = listCart.findIndex((obj) => obj.id == newData.id);
      if (objIndex != -1) {
        listCart[objIndex].cantidad += 1;
      } else {
        listCart.push(newData);
      }
    } else {
      listCart = [];
      listCart.push(newData);
    }
    this.cart.next(listCart);
  }

  public removeEmenetCart(newData: Producto) {
    let listCart = this.cart.getValue();
    let objIndex = listCart.findIndex((obj) => obj.id == newData.id);
    if (objIndex != -1) {
      listCart[objIndex].cantidad = 1;
      listCart.splice(objIndex, 1);
    }
    this.cart.next(listCart);
  }
}
