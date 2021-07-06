import { Component, OnInit, ViewChild } from '@angular/core';
import { MatMenuTrigger } from '@angular/material/menu';
import { Router } from '@angular/router';
import { SocialAuthService, SocialUser } from 'angularx-social-login';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;

  userLogged: SocialUser;
  isLogged: boolean;
  isLoggedSocial: boolean;
  public openCart: boolean = false;
  userName: string;

  constructor(
    private authServiceSocial: SocialAuthService,
    private tokenService: TokenService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.userName = this.tokenService.getUserName();
      //this.userLogged.name = this.tokenService.getUserName();
    } else {
      this.isLogged = false;
      this.authServiceSocial.authState.subscribe((data) => {
        this.userLogged = data;
        this.isLogged =
          this.userLogged != null && this.tokenService.getToken() != null;
      });
    }
  }

  logOut(): void {
    this.tokenService.logOut();
    this.authServiceSocial.signOut().then((data) => {
      this.tokenService.logOut();
      this.router.navigate(['/login']);
    });
    /* this.authServiceSocial.signOut().then((data) => {
      this.router.navigate(['/login']);
    });*/
    window.location.reload();
  }

  public cart() {
    this.openCart = !this.openCart;
  }

  someMethod() {
    this.trigger.openMenu();
  }
}
