import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { facturas } from '../models/facturas';

@Injectable({
  providedIn: 'root'
})
export class FacturaService {


  constructor(private http: HttpClient) { }

  listar='http://localhost:9000/api/v1/factura/all';
  listarId='http://localhost:9000/api/v1/factura';
  delete='http://localhost:9000/api/v1/factura/id';

  getFacturas(){
    return this.http.get<facturas[]>(this.listar);
  }

  createFactura(facturas: facturas){return this.http.post<facturas[]>(this.listarId,facturas)}
  deleteFacturas(){return this.http.delete<facturas[]>(this.delete);}
  
  
}
