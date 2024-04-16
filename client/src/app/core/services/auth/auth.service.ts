import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {shareReplay, tap} from "rxjs";
import moment from "moment";

export interface User {
  email: string,
  password: string
}

export interface AuthResult {
  expiresIn: string,
  idToken: string
}

enum LocalStorageToken {
  idToken = 'id_token',
  expiredAt = "expires_at"
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(email: string, password: string) {
    return this.http.post<User>('/api/login', {email, password})
      .pipe(
        tap(res => this.setSession),
        shareReplay()
      )
  }

  private setSession(authResult: AuthResult) {
    const expiresAt = moment().add(authResult.expiresIn, 'second')

    localStorage.setItem(LocalStorageToken.idToken, authResult.idToken);
    localStorage.setItem(LocalStorageToken.expiredAt, JSON.stringify(expiresAt.valueOf()));
  }

  public logout() {
    localStorage.removeItem(LocalStorageToken.idToken)
    localStorage.removeItem(LocalStorageToken.expiredAt)
  }

  public isLoggedIn() {
    return moment().isBefore(this.getExpiration())
  }

  public isLoggedOut() {
    return !this.isLoggedIn();
  }

  public getExpiration() {
    const expiration = localStorage.getItem(LocalStorageToken.expiredAt);
    //if expiredAt is missing from local storage then return time from the past
    if(!expiration) {
      return moment().subtract(10000, 'second')
    }
    const expiresAt = JSON.parse(expiration);
    return moment(expiresAt);


  }
  public getToken() {
    return localStorage.getItem(LocalStorageToken.idToken);
  }
}
