import { Routes } from '@angular/router';
import {LoginComponent} from "./core/auth/pages/login/login.component";
import {HomeComponent} from "./shared/pages/home/home.component";
import {authGuard} from "./core/guards/auth.guard";
import {AddRaportFormComponent} from "./features/raports/pages/add-raport-form/add-raport-form.component";
import {TemplateFormComponent} from "./shared/components/template-form/template-form.component";

export const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'add-raport', component: AddRaportFormComponent},
  {path: 'templateForm', component: TemplateFormComponent},
  {path: '', component: HomeComponent, canActivate: [authGuard]}
];
