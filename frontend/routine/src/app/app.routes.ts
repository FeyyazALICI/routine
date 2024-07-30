import { Routes } from '@angular/router';
import { HomeComponent } from './components/principal/home/home.component';
import { PerformanceComponent } from './components/principal/performance/performance/performance.component';
import { DataEntryComponent } from './components/principal/data_entry/data-entry/data-entry.component';

export const routes: Routes = [
    {path: "" , component:HomeComponent},
    {path: "home", component:HomeComponent},
    {path: "performance", component:PerformanceComponent},
    {path: "data_registry", component:DataEntryComponent}
];
