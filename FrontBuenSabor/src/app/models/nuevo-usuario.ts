export class NuevoUsuario {
  usuario: string;
  email: string;
  password: string;
  authorities: string[];

  constructor(usuario: string, email: string, password: string) {
    this.usuario = usuario;
    this.email = email;
    this.password = password;
  }
}
