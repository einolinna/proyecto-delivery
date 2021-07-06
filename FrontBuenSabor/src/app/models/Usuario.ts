import { Cliente } from './Cliente';

export class Usuario {
  id: number;
  usuario: string;
  email: string;
  clave: string;
  roles: string[];
  cliente: Cliente;

  constructor(usuario: string, email: string, clave: string, cliente: Cliente) {
    this.usuario = usuario;
    this.email = email;
    this.clave = clave;
    this.cliente = cliente;
    this.roles = ['cliente'];
  }
}
