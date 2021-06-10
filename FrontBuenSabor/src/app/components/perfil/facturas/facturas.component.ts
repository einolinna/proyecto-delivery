import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SocialAuthService, SocialUser } from 'angularx-social-login';
import { facturas } from 'src/app/models/facturas';
import { FacturaService } from 'src/app/services/factura.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-facturas',
  templateUrl: './facturas.component.html',
  styleUrls: ['./facturas.component.css'],
})
export class FacturasComponent implements OnInit {
  userLogged: SocialUser;
  isLogged = false;
  isLoggedSocial: boolean;

  facturas: facturas[];
  constructor(
    private service: FacturaService,
    private router: Router,
    private authServiceSocial: SocialAuthService,
    private tokenService: TokenService
  ) {}

  //Ruta Create Factura
  nuevaFactura() {
    this.router.navigate(['nuevaFactura']);
  }

  //Ruta Update Factura

  editarFactura(){this.router.navigate(['editarFactura']);}

  //Ruta Delete Factura

  borrarFactura(){this.router.navigate(['borrarFactura']);}



  ngOnInit(): void {
    this.service.getFacturas().subscribe((data) => {
      this.facturas = data;
    });

    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
    this.authServiceSocial.authState.subscribe((data) => {
      this.userLogged = data;
      this.isLogged = this.userLogged != null;
      this.isLoggedSocial = this.userLogged != null;
    });
  }
}
