import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  description_img:string = "assets/image/home/description.png";

  navigateOutsideF(entry: string) {
    window.open(entry, '_blank');
  }
}
