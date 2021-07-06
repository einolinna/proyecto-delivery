import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Cliente } from '../models/Cliente';
import { Domicilio } from '../models/domicilio';
import { NuevoUsuario } from '../models/nuevo-usuario';
import { Usuario } from '../models/Usuario';
import { AuthService } from '../services/auth.service';
import { TokenService } from '../services/token.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css'],
})
export class RegistroComponent implements OnInit {
  nuevoUsuario: NuevoUsuario;
  nombreUsuario: string;
  usuario: string;
  email: string;
  password: string;
  isLogged = false;
  cliente: Cliente = new Cliente();
  domicilio: Domicilio = new Domicilio();
  usuarioP: Usuario;

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    }
  }

  onRegister(): void {
    if (this.tokenService.getToken()) {
      this.update();
    } else {
      //crear cliente
      this.create();
    }
    console.log(this.isLogged);
  }

  create() {
    this.nuevoUsuario = new NuevoUsuario(
      this.usuario,
      this.nombreUsuario,
      this.email,
      this.password
    );
    this.authService.nuevo(this.nuevoUsuario).subscribe(
      (data) => {
        this.toastr.success('Cuenta Creada', 'OK', {
          timeOut: 3000,
          positionClass: 'toast-top-center',
        });
        this.router.navigate(['/login']);
      },
      (err) => {
        this.toastr.error('No fue posible crear su cuenta', 'Fail', {
          timeOut: 3000,
          positionClass: 'toast-top-center',
        });
      }
    );
  }

  update() {
    this.cliente.domicilio = this.domicilio;
    this.usuarioP = new Usuario(
      this.usuario,
      this.email,
      this.password,
      this.cliente
    );
    this.authService.cargar(this.usuarioP).subscribe(
      (data) => {
        this.toastr.success('Cuenta Actualizada', 'OK', {
          timeOut: 3000,
          positionClass: 'toast-top-center',
        });
        this.router.navigate(['/']);
      },
      (err) => {
        this.toastr.error('No fue posible crear su cuenta', 'Fail', {
          timeOut: 3000,
          positionClass: 'toast-top-center',
        });
      }
    );
  }
}
