import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Cliente } from 'src/app/models/Cliente';
import { Domicilio } from 'src/app/models/domicilio';
import { NuevoUsuario } from 'src/app/models/nuevo-usuario';
import { Usuario } from 'src/app/models/Usuario';
import { AuthService } from 'src/app/services/auth.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css'],
})
export class UsuarioComponent implements OnInit {
  nuevoUsuario: NuevoUsuario;
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
      this.usuario = this.tokenService.getUserName();
      console.log(this.tokenService.getUserName());
    }
  }

  updatePass() {
    console.log('se envia la actualización y el usuario es ' + this.usuario);
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
