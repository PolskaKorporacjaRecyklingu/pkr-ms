import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {of, shareReplay, tap} from "rxjs";
import moment from "moment";
import {environment} from "../../../../environments/environment";

export interface User {
  email: string,
  password: string
}

export interface AuthResult {
  expirationDate: string,
  token: string
}

enum LocalStorageToken {
  idToken = 'id_token',
  expiredAt = "expires_at"
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  urlAddress: string = environment.apiUrl

  constructor(private http: HttpClient) { }

  public login(email: string, password: string) {

    const mockAuthResult: AuthResult = {
      expirationDate: "2024-05-07T19:51:12.000",
      token: "XDXDeyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJyZWN5Y2xpbmdhZG1pbiIsImlhdCI6MTcxNTA5NjE3MiwiZXhwIjoxNzE1MDk3MDcyfQ.gGLyrPWagni78C6uV0mKi0dZFkFTPTecufUd0Vcaz3Lxt2LTuPAyl68wo7I0hPB2"
    }
    return of(mockAuthResult)
      .pipe(
        tap(res=> this.setSession(res)),
        // shareReplay()
      )

    // return this.http.post<AuthResult>(this.urlAddress + '/api/login', {email, password})
    //   .pipe(
    //     tap(res => this.setSession),
    //     shareReplay()
    //   )
  }

  private setSession(authResult: AuthResult) {
    const expiresAt = moment(authResult.expirationDate)

    localStorage.setItem(LocalStorageToken.idToken, authResult.token);
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
