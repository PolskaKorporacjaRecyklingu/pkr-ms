import { Routes } from '@angular/router';
import {LoginComponent} from "./core/auth/pages/login/login.component";
import {HomeComponent} from "./shared/pages/home/home.component";

export const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', component: HomeComponent}
];