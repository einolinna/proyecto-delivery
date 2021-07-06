import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login.component';
import { ProductosComponent } from './components/productos/productos.component';
import { RegistroComponent } from './auth/registro.component';

import { ToastrModule } from 'ngx-toastr';
//login social
import {
  SocialLoginModule,
  SocialAuthServiceConfig,
} from 'angularx-social-login';
import { GoogleLoginProvider } from 'angularx-social-login';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { interceptorProvider } from './services/interceptors/prod-interceptor.service';
import { FormsModule } from '@angular/forms';
import { PerfilComponent } from './components/perfil/perfil.component';
import { FacturasComponent } from './components/facturas/facturas.component';
import { PedidosComponent } from './components/pedidos/pedidos.component';
import { FacturaService } from './services/factura.service';
import { CreateComponent } from './components/facturas/create/create.component';
import { DeleteComponent } from './components/facturas/delete/delete.component';
import { ItemProductoComponent } from './components/productos/item-producto/item-producto.component';
import { ProductoDetalleComponent } from './components/productos/producto-detalle/producto-detalle.component';
import { CarritoComponent } from './components/carrito/carrito.component';
import { UpdateComponent } from './components/facturas/update/update.component';
import { UsuarioComponent } from './components/perfil/usuario/usuario.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ProductosComponent,
    RegistroComponent,
    NavbarComponent,
    HomeComponent,
    PerfilComponent,
    FacturasComponent,
    PedidosComponent,
    CreateComponent,
    DeleteComponent,
    ItemProductoComponent,
    ProductoDetalleComponent,
    CarritoComponent,
    UpdateComponent,
    UsuarioComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    SocialLoginModule,
    ToastrModule.forRoot(),
    FormsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatMenuModule,
  ],
  providers: [
    interceptorProvider,
    FacturaService,
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
              '162688534070-llobug80jihe4dja4qi8mk9fbfc65fc4.apps.googleusercontent.com'
            ),
          } /* ,
        {
          id: FacebookLoginProvider.PROVIDER_ID,
          provider: new FacebookLoginProvider('clientId')
        } */,
        ],
      } as SocialAuthServiceConfig,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
