import { Component, ViewChild } from '@angular/core';
import { Router,NavigationEnd  } from '@angular/router';
import { CommonModule } from '@angular/common';

// ngPrimeImports
import { ButtonModule } from 'primeng/button';
import { MenuModule } from 'primeng/menu';
import { MenuItem } from 'primeng/api';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { OverlayPanel } from 'primeng/overlaypanel';
import { ArtPComponent } from "../performanceComponents/art-p/art-p.component";
import { ProjectPComponent } from "../performanceComponents/project-p/project-p.component";
import { SportPComponent } from "../performanceComponents/sport-p/sport-p.component";

@Component({
  selector: 'app-performance',
  standalone: true,
  imports: [ButtonModule, MenuModule, OverlayPanelModule, ArtPComponent, ProjectPComponent, SportPComponent, CommonModule],
  templateUrl: './performance.component.html',
  styleUrl: './performance.component.css'
})
export class PerformanceComponent {

  sportActive:boolean   = false;
  artActive:boolean     = false;
  projectActive:boolean = false;
  activeComponent(entry:string){
    if(entry == 'sport'){
      this.sportActive   = true;
      this.artActive     = false;
      this.projectActive = false;
    }
    if(entry == 'art'){
      this.sportActive   = false;
      this.artActive     = true;
      this.projectActive = false;
    }
    if(entry == 'project'){
      this.sportActive   = false;
      this.artActive     = false;
      this.projectActive = true;
    }
  }


}
