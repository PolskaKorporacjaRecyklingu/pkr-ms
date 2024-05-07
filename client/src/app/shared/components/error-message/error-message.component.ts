import {Component, Input} from '@angular/core';
import { ValidationErrors} from "@angular/forms";
import { MatFormFieldModule} from "@angular/material/form-field";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-error-message',
  standalone: true,
  imports: [
    MatFormFieldModule,
    CommonModule
  ],
  templateUrl: './error-message.component.html',
  styleUrl: './error-message.component.scss'
})
export class ErrorMessageComponent {
  @Input() errors!:  ValidationErrors
}
