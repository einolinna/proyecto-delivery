import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { FacturasComponent } from './components/perfil/facturas/facturas.component';
import { PedidosComponent } from './components/perfil/pedidos/pedidos.component';
import { HomeComponent } from './components/home/home.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { ProductosComponent } from './components/productos/productos.component';

import { CreateComponent } from './components/perfil/facturas/create/create.component';
import { UpdateComponent } from './components/perfil/facturas/update/update.component';
import { DeleteComponent } from './components/perfil/facturas/delete/delete.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'perfil', component: PerfilComponent },
  { path: 'lista', component: ProductosComponent },
  { path: 'facturas', component:FacturasComponent },
  { path: 'nuevaFactura', component:CreateComponent},
  { path: 'editarFactura', component:UpdateComponent},
  { path: 'borrarFactura', component:DeleteComponent},
  { path: 'pedidos', component:PedidosComponent},
  { path: '**', redirectTo: 'home', pathMatch: 'full' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
