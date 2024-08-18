import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { CommonModule } from '@angular/common';

// PrimeNG Imports
import { ButtonModule } from 'primeng/button';
import { MenuModule } from 'primeng/menu';
import { MenuItem } from 'primeng/api';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { OverlayPanel } from 'primeng/overlaypanel';
import { ToastrSerService } from '../../../../../models_and_services/services/auxilary/toastr-ser.service';
import { ApiSerService } from '../../../../../models_and_services/services/apiServices/api-ser.service';
import { DoubleAttrDao } from '../../../../../models_and_services/models/dao/DoubleAttrDao';
import { DoubleAttrNumericDao } from '../../../../../models_and_services/models/dao/DoubleAttrNumericDao';
import { ChartModule } from 'primeng/chart';  // Import ChartModule
import { DoubleAttrDateDao } from '../../../../../models_and_services/models/dao/DoubleAttrDateDao';
import { TrippleAttrNumericDao } from '../../../../../models_and_services/models/dao/TrippleAttrNumericDao';
import { Constants } from '../../../../../models_and_services/common_constants/Constants';

@Component({
  selector: 'app-project-p',
  standalone: true,
  imports: [ButtonModule, MenuModule, OverlayPanelModule, CommonModule, ChartModule],
  templateUrl: './project-p.component.html',
  styleUrl: './project-p.component.css'
})
export class ProjectPComponent implements OnInit {

  constructor(
    private toastrSer: ToastrSerService,
    private apiSer: ApiSerService
  ) { }



  selectedsubcategory: number = null;
  selectedTime: number = null;

  // Button activation
  active1: boolean = false;
  active2: boolean = false;
  active3: boolean = false;
  active4: boolean = false;

  activeTime0: boolean = false;
  activeTime1: boolean = false;
  activeTime2: boolean = false;
  activeTime3: boolean = false;
  activeTime4: boolean = false;

  chartData: any;
  graphData: DoubleAttrDateDao[];
  chartOptions: any;

  ngOnInit(): void {
    this.initializeChartOptions();
  }

  activesubcategory(entry: number) {
    this.resetsubcategory();
    switch (entry) {
      case 1: this.active1 = true; break;
      case 2: this.active2 = true; break;
      case 3: this.active3 = true; break;
      case 4: this.active4 = true; break;
    }
    this.selectedsubcategory = entry;
  }

  resetsubcategory() {
    this.active1 =  false;
    this.active2 =  false;
    this.active3 =  false;
    this.active4 =  false;
  }

  resetTimeSelection() {
    this.activeTime0 = false;
    this.activeTime1 = false;
    this.activeTime2 = false;
    this.activeTime3 = false;
    this.activeTime4 = false;
  }

  activeTimeLine(entry: number) {
    this.resetTimeSelection();
    switch (entry) {
      case 0: this.activeTime0 = true; break;
      case 1: this.activeTime1 = true; break;
      case 2: this.activeTime2 = true; break;
      case 3: this.activeTime3 = true; break;
      case 4: this.activeTime4 = true; break;
    }
    this.selectedTime = entry;
  }

  dateFormatterF(entry: string): Date {
    const [day, month, year] = entry.split('.');
    return new Date(parseInt(year, 10), parseInt(month, 10) - 1, parseInt(day, 10));
  }

  search() {
    if (this.selectedsubcategory == null || this.selectedTime == null) {
      this.toastrSer.showError("Both Sport & Time Constraints Should be Selected!");
    } else {
      let data = new TrippleAttrNumericDao(Constants.project, this.selectedsubcategory, this.selectedTime);
      this.apiSer.performance(data).subscribe(
        (response: any) => {
          this.graphData = response.data.map((item: DoubleAttrDao) => ({
            attr0: this.dateFormatterF(item.attr0),
            attr1: parseFloat(item.attr1)
          }));
          this.updateChartData(); // Update chart data after fetching and processing
          this.toastrSer.showSuccess("Graph Created!");
        },
        (error: any) => {
          console.error('Error fetching data', error);
        }
      );
    }
  }

  updateChartData() {
    this.chartData = {
      labels: this.graphData.map(item => item.attr0.toDateString()), // attr0 is now a Date
      datasets: [
        {
          label: 'Performance',
          data: this.graphData.map(item => item.attr1),
          fill: false,
          borderColor: '#42A5F5',
          tension: 0.4
        }
      ]
    };
  }

  initializeChartOptions() {
    this.chartOptions = {
      responsive: true,
      plugins: {
        legend: {
          position: 'top'
        },
        tooltip: {
          mode: 'index',
          intersect: false
        }
      },
      hover: {
        mode: 'nearest',
        intersect: true
      },
      scales: {
        x: {
          display: true,
          title: {
            display: true,
            text: 'Date'
          }
        },
        y: {
          display: true,
          title: {
            display: true,
            text: 'Value'
          }
        }
      }
    };
  }
}
