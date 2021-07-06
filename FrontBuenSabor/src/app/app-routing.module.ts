import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { FacturasComponent } from './components/facturas/facturas.component';
import { PedidosComponent } from './components/pedidos/pedidos.component';
import { HomeComponent } from './components/home/home.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { ProductosComponent } from './components/productos/productos.component';

import { CreateComponent } from './components/facturas/create/create.component';
import { DeleteComponent } from './components/facturas/delete/delete.component';
import { ProductoDetalleComponent } from './components/productos/producto-detalle/producto-detalle.component';
import { UpdateComponent } from './components/facturas/update/update.component';
import { ProductoGuard } from './guards/producto.guard';
import { UsuarioComponent } from './components/perfil/usuario/usuario.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'perfil', component: PerfilComponent },
  { path: 'perfil/usuario', component: UsuarioComponent },
  {
    path: 'lista',
    component: ProductosComponent,
    canActivate: [ProductoGuard],
  },
  { path: 'facturas', component: FacturasComponent },
  { path: 'nuevaFactura', component: CreateComponent },
  { path: 'eliminarFactura', component: DeleteComponent },
  { path: 'editarFactura', component: UpdateComponent },
  { path: 'pedidos', component: PedidosComponent },
  {
    path: 'productos/detalle/:id/:insumo',
    component: ProductoDetalleComponent,
  },
  { path: '**', redirectTo: 'home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
