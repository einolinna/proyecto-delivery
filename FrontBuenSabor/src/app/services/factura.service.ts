import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { facturas } from '../models/facturas';

@Injectable({
  providedIn: 'root'
})
export class FacturaService {

  facturas:facturas[];
  constructor(private http: HttpClient) { }

  listar='http://localhost:9000/api/v1/factura/all';
  listarId='http://localhost:9000/api/v1/factura';
  delete='http://localhost:9000/api/v1/factura/id';

  getFacturas(){
    return this.http.get<facturas[]>(this.listar);
  }
  getFacturasbyNumero(numero:facturas){
    return this.http.get<facturas[]>(this.listarId+"/"+numero);
  }
  createFactura(facturas: facturas[]){return this.http.post<facturas[]>(this.listarId,facturas)}
  deleteFacturas(facturas: facturas):Observable<Object>{return this.http.delete(this.listarId+"/"+facturas.numero)}
  editarFactura(facturas: facturas){return this.http.put<facturas[]>(this.listarId+"/"+facturas.numero,facturas)}
  
  
}
