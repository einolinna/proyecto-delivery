import { Component, OnInit } from '@angular/core';
import { SocialAuthService, SocialUser } from 'angularx-social-login';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  userLogged: SocialUser;
  isLogged: boolean;
  nombreUsuario = '';
  constructor(
    private tokenService: TokenService,
    private authServiceSocial: SocialAuthService
  ) {}

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.nombreUsuario = this.tokenService.getUserName();
      //this.userLogged.name = this.tokenService.getUserName();
    } else {
      this.authServiceSocial.authState.subscribe((data) => {
        this.userLogged = data;
        console.log(data);
        this.isLogged =
          this.userLogged != null && this.tokenService.getToken() != null;
      });
    }
    if (!this.tokenService.getToken()) {
      this.isLogged = false;
      this.nombreUsuario = '';
    }
  }
}
