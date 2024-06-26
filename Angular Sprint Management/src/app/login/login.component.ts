import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationServiceService } from '../service/authentication-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  invalidCredentials = false;
  authenticated : boolean = false;
  constructor(private router : Router, private authenticateUsersService:AuthenticationServiceService){}

  loginForm !: FormGroup;

  ngOnInit() : void{
    this.router.navigate(['login']);
    this.loginForm = new FormGroup(
      {
        userName : new FormControl('',[Validators.required]),
        password : new FormControl('',[Validators.required]),
      }
    )
  }

  

checkLogin(){
    this.authenticateUsersService.authenticate(this.loginForm.get('userName')?.value, this.loginForm.get('password')?.value).subscribe(
      data=>{
      this.invalidCredentials = false;
      this.router.navigate(['home'])
      },
      error=>{
      this.invalidCredentials = true;
      }
    )
  }


}