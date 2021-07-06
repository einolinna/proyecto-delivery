import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SocialAuthService, SocialUser } from 'angularx-social-login';
import { TokenService } from 'src/app/services/token.service';


@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {
  userLogged: SocialUser;
  isLogged = false;
  isLoggedSocial: boolean;

  constructor(private router: Router, private authServiceSocial: SocialAuthService,
    private tokenService: TokenService) { }
  
  verFacturas(){

    this.router.navigate(['facturas'])
  }

  verPedidos(){

    this.router.navigate(['pedidos'])
  }
 
 

  ngOnInit(): void {
   
  }
}
