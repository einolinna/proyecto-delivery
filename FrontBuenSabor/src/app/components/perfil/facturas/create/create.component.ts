import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { facturas } from 'src/app/models/facturas';
import { FacturaService } from 'src/app/services/factura.service';


@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  constructor(private router:Router,private service:FacturaService) { }

  ngOnInit(): void {}
   
    Guardar(facturas:facturas) {
      this.service.createFactura(facturas)
      .subscribe(data => {
        alert("Factura añadida con exito!")
        this.router.navigate(['facturas']);
      })
    }
 

}
