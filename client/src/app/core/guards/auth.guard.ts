import {ActivatedRouteSnapshot, CanActivateFn, NavigationExtras, Router, RouterStateSnapshot} from '@angular/router';
import {inject} from "@angular/core";

export const authGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  let isAuthenticated = false;
  const router = inject(Router);
  console.log('authGuard#canActivate called');
  if(isAuthenticated) {
    return true;
  }
  return router.createUrlTree(['/login'])

};
