import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {
  GoogleLoginProvider,
  SocialAuthService,
  SocialUser,
} from 'angularx-social-login';
import { LoginUsuario } from '../models/login-usuario';
import { AuthService } from '../services/auth.service';
import { TokenService } from '../services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  isLogged = false;
  isLoginFail = false;
  loginUsuario: LoginUsuario;
  nombreUsuario: string;
  password: string;
  roles: string[] = [];
  errMsj: string;
  socialUser: SocialUser;
  userLogged: SocialUser;

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private authServiceSocial: SocialAuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.isLoginFail = false;
      this.roles = this.tokenService.getAuthorities();
    }
    this.authServiceSocial.authState.subscribe((data) => {
      this.userLogged = data;
      this.isLogged = this.userLogged != null;
    });
  }

  onLogin(): void {
    this.loginUsuario = new LoginUsuario(this.nombreUsuario, this.password);
    this.authService.login(this.loginUsuario).subscribe(
      (data) => {
        this.isLogged = true;
        this.isLoginFail = false;

        this.tokenService.setToken(data.token);
        this.tokenService.setUserName(data.usuario);
        this.tokenService.setAuthorities(data.authorities);
        this.roles = data.authorities;
        this.router.navigate(['/']);
      },
      (err) => {
        this.isLogged = false;
        this.isLoginFail = true;
        this.errMsj = err.error.message;
        //console.log(err.error.message);
      }
    );
  }

  signInWithGoogle(): void {
    this.authServiceSocial
      .signIn(GoogleLoginProvider.PROVIDER_ID)
      .then((data) => {
        this.userLogged = data;
        this.isLogged = true;
        this.router.navigate(['/']);
      });
  }

  logOut(): void {
    this.authServiceSocial.signOut();
  }
}
