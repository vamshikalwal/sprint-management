import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserDto } from '../datatransferobject/user-dto';
import { firstValueFrom } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationServiceService {
 

  constructor(private http:HttpClient, private router : Router) { }
  authenticatedUrl : string = "http://localhost:8088/authenticate/users";
  authentiCatedUser !: UserDto;
  authentiCatedUserBacked !: UserDto;
  authenticated !: boolean;
  users !: UserDto[];

  getAuthenticatedUser(user:UserDto){
    return firstValueFrom(this.http.post<UserDto>(this.authenticatedUrl,user));
  }

authenticate(userName: string,password : string){
  this.authentiCatedUser = new UserDto();
  this.authentiCatedUser.userName = userName;
  this.authentiCatedUser.password = password;

  this.http.post<UserDto>(this.authenticatedUrl,this.authentiCatedUser).subscribe(
    data=>{

      console.log(data);
      this.authenticated = true;
      sessionStorage.setItem('username',data.userName);
      sessionStorage.setItem('role',data.role);
    },
    error=>{
      this.authenticated = false;
    }
  )


  return this.http.post<UserDto>(this.authenticatedUrl,this.authentiCatedUser);
}


  isUserLoggedIn(){
    let user = sessionStorage.getItem('username');
    let role=sessionStorage.getItem('role');
    return !(user === null && role===null);
  }

  isUserAdmin(){
    let role=sessionStorage.getItem('role');
    return (role==='admin');
  }


  logOut(){
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('role');
    this.router.navigateByUrl('/login');
  }

}
