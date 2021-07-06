import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtDTO } from '../models/jwt-dto';
import { LoginUsuario } from '../models/login-usuario';
import { NuevoUsuario } from '../models/nuevo-usuario';
import { TokenDto } from '../models/TokenDto';
import { Usuario } from '../models/Usuario';

const cabecera = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  authURL = 'http://localhost:9000/api/v1/usuario/auth/';

  constructor(private httpClient: HttpClient) {}

  public nuevo(nuevoUsuario: NuevoUsuario): Observable<any> {
    return this.httpClient.post<any>(
      this.authURL + 'crear-cliente',
      nuevoUsuario
    );
  }

  public login(loginUsuario: LoginUsuario): Observable<JwtDTO> {
    return this.httpClient.post<JwtDTO>(this.authURL + 'login', loginUsuario);
  }

  public cargar(usuario: Usuario): Observable<Usuario> {
    return this.httpClient.post<Usuario>(this.authURL + 'cargar', usuario);
  }

  public google(tokenDto: TokenDto): Observable<TokenDto> {
    try {
      return this.httpClient.post<TokenDto>(
        this.authURL + 'google',
        tokenDto,
        cabecera
      );
    } catch (error) {
      console.log(error);
    }
  }
}
