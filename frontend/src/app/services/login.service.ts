import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export  class LoginService{
  token: string
  constructor (private http: Http) {}

    sendCredentials(model){
      let tokenUrl = 'http://localhost:8080/user/login';
      let headers1 = new Headers({'Content-type':"application/json"});

      return this.http.post(tokenUrl, JSON.stringify(model), {headers:headers1});
    }

    // sendToken(token){
    //   let tokenUrl = 'http://localhost:8080/user/login';
    //   let headers1 = new Headers({'Content-type':"application/json"});
    //
    //   return this.http.post(tokenUrl, JSON.stringify(model), {headers:headers1});
    // }

    checkLogin() {
      if(localStorage.getItem("currentUserName") != ""){
        return true;
      } else {
        return false;
      }
    }

    logout() {
      localStorage.setItem("currentUserName", "");
      alert("you just logged out.");
    }
  }
