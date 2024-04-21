import {Component, OnInit} from '@angular/core';
import {NavComponent} from "./shared/components/nav/nav.component";
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [NavComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent{
  title = 'client';
}
