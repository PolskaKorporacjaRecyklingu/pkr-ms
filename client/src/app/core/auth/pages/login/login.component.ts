import { Component } from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthService} from "../../../services/auth/auth.service";
import {MatCard, MatCardModule} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {CommonModule} from "@angular/common";
import {MatButton} from "@angular/material/button";
import {ErrorMessageComponent} from "../../../../shared/components/error-message/error-message.component";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatCardModule, MatFormFieldModule, MatInputModule, CommonModule, ReactiveFormsModule, MatButton, ErrorMessageComponent
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginControl = this.fb.group({
    login: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.minLength(8), Validators.required]]
  })
  constructor(private fb: FormBuilder, private authService: AuthService) {}

  get login() {
    return this.loginControl.get('login')
  }
  get password() {
    return this.loginControl.get('password')
  }
  public onSubmit() {
    console.log("onSubmit");
    let login = this.login?.value
    let password = this.password?.value
    if(this.loginControl.status === "INVALID") {
      console.log("Invalid")
      return
    }
    if(login === null || login === undefined) {
      return
    }
    if(password === null || password === undefined) {
      return
    }
    let responseObs = this.authService.login(login, password).subscribe(response => {
      console.log(response.token)
    })
  }

  public showErrors() {

    console.log(this.loginControl.get('login'));
  }
}
