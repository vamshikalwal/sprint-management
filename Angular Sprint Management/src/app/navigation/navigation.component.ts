import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationServiceService } from '../service/authentication-service.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css'
})
export class NavigationComponent {

  authenticated : boolean = false;
 
  constructor(private router : Router, public autheticateUsersService : AuthenticationServiceService){
    if(this.autheticateUsersService.isUserLoggedIn()){
      this.router.navigate(['home']);
    }
  }
 

 
  logout(){
    this.autheticateUsersService.logOut();
    this.router.navigate(['login']);
  }


}
