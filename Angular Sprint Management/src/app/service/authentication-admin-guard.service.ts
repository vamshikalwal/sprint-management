import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { AuthenticationServiceService } from './authentication-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationAdminGuardService {

  constructor(private router: Router,
    private authService: AuthenticationServiceService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) : boolean{

    if (this.authService.isUserLoggedIn() && this.authService.isUserAdmin()){
      return true;
    }
    else{
      this.router.navigate(['login']);
        return false;
    }

  }
}
