import { HttpInterceptorFn } from '@angular/common/http';
import {inject} from "@angular/core";
import {AuthService} from "../../services/auth/auth.service";

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {

  let authService = inject(AuthService)
  const idToken = authService.getToken();

  if(idToken) {
    const cloned = req.clone({
      headers: req.headers.set("Authentication", "Bearer " + idToken)
    })
    return next(cloned);
  }
  return next(req);
};
