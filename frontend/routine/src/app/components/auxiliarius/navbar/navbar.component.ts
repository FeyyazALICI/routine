import { Component, ViewChild } from '@angular/core';
import { Router,NavigationEnd  } from '@angular/router';

// ngPrimeImports
import { ButtonModule } from 'primeng/button';
import { MenuModule } from 'primeng/menu';
import { MenuItem } from 'primeng/api';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { OverlayPanel } from 'primeng/overlaypanel';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [ButtonModule, MenuModule, OverlayPanelModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  menuItems: MenuItem[];
  currentPath: string; // Store current path here
  ngOnInit() {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.currentPath = event.urlAfterRedirects; // Get current path on route change
        console.log(this.currentPath);
      }
    });
  }
  constructor(
    private router:Router,
  ){}


  navigate(path_to_go:string){
    this.router.navigate([path_to_go])
  }

  // Deciding Page Titles ----------------------------------------------------------
  pageCode:string =         "ANA SAYFA";
  pageDescription:string =  "TOPLAM EKİPMAN ETKİNLİĞİ";
  // Deciding Page Titles ----------------------------------------------------------

  page_name_f(pageCodeS:string, pageDescriptionS:string){
    this.pageCode =pageCodeS;
    this.pageDescription = pageDescriptionS;
  }

}
