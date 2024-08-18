import { Component, ViewChild } from '@angular/core';
import { Router,NavigationEnd  } from '@angular/router';
import { CommonModule } from '@angular/common';

// ngPrimeImports
import { ButtonModule } from 'primeng/button';
import { MenuModule } from 'primeng/menu';
import { MenuItem } from 'primeng/api';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { OverlayPanel } from 'primeng/overlaypanel';


// importing auxilary components
import { ArtEComponent } from '../data-entry-components/art-e/art-e.component';
import { ProjectEComponent } from '../data-entry-components/project-e/project-e.component';
import { SportEComponent } from '../data-entry-components/sport-e/sport-e.component';
import { SportPComponent } from "../../performance/performanceComponents/sport-p/sport-p.component";

@Component({
  selector: 'app-data-entry',
  standalone: true,
  imports: [ButtonModule, MenuModule, OverlayPanelModule, ArtEComponent, ProjectEComponent, SportEComponent, CommonModule, SportPComponent],
  templateUrl: './data-entry.component.html',
  styleUrl: './data-entry.component.css'
})
export class DataEntryComponent {
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