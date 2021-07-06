import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Preferencia } from '../models/Preferencia';
import { Producto } from '../models/producto';

@Injectable({
  providedIn: 'root',
})
export class MercadoPagoService {
  //editar base endpoint, crear back
  protected baseEndpoint = 'http://localhost:9000/api/v1/producto';
  protected cabeceras: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });
  constructor(private http: HttpClient) {}
  public procesarPago(
    producto: Producto,
    cantidad: number
  ): Observable<Preferencia> {
    return this.http.post<Preferencia>(
      `${this.baseEndpoint}/create_preference/${cantidad}`,
      producto,
      {
        headers: this.cabeceras,
      }
    );
  }
}
