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
  f:facturas= new facturas();
  constructor(
    private FacturaService: FacturaService,
    private router: Router,
  ) {}

  //Ruta Create Factura
  nuevaFactura() {
    this.router.navigate(['nuevaFactura']);
  }

  //Ruta Update Factura

editarFactura() {this.router.navigate(['editarFactura']);}
    

    
    delete(factura){
      if(confirm("Esta seguro que desea eliminar esta factura?")){
      this.FacturaService.deleteFacturas(factura)
      .subscribe(data =>{
        this.facturas = this.facturas!.filter(f=>f!==factura);
        this.router.navigate(['facturas'])
        
      
    })}
    }
          
      

  ngOnInit(): void {
    this.FacturaService.getFacturas().subscribe((data) => {
      this.facturas = data;
    });


  }
}
