import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ArticuloInsumo } from '../models/ArticuloInsumo';
import { ArticuloMfact } from '../models/ArticuloMfact';

@Injectable({
  providedIn: 'root',
})
export class ProductosService {
  protected url = 'http://localhost:9000/api/v1';
  protected cabeceras: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });
  constructor(private httpClient: HttpClient) {}

  public getLikeArtInsumo(): Observable<ArticuloInsumo[]> {
    return this.httpClient.get<ArticuloInsumo[]>(
      this.url + '/articuloinsumo/like'
    );
  }

  public getArtInsumoById(id: number): Observable<ArticuloInsumo> {
    return this.httpClient.get<ArticuloInsumo>(
      `${this.url}/articuloinsumo/${id}`
    );
  }
  public getLikeArtMfact(): Observable<ArticuloMfact[]> {
    return this.httpClient.get<ArticuloMfact[]>(
      this.url + '/articulomanufaturado/like'
    );
  }
  public getArtMfactById(id: number): Observable<ArticuloMfact> {
    return this.httpClient.get<ArticuloMfact>(
      `${this.url}/articulomanufaturado/${id}`
    );
  }
}
