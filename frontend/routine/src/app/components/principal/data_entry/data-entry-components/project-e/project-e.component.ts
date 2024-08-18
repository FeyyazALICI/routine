import { Component, OnInit } from '@angular/core';
import { CardModule } from 'primeng/card';
import { ApiSerService } from '../../../../../models_and_services/services/apiServices/api-ser.service';
import { FormsModule } from '@angular/forms';

// ng-prime imports
import { CommonModule } from '@angular/common';
import { DropdownModule } from 'primeng/dropdown';
import { InputNumberModule } from 'primeng/inputnumber';
import { ButtonModule } from 'primeng/button';
import { ToastrService } from 'ngx-toastr';
import { ToastrSerService } from '../../../../../models_and_services/services/auxilary/toastr-ser.service';
import { SaveSingleEntityDao } from '../../../../../models_and_services/models/dao/SaveSingleEntityDao';
import { Constants } from '../../../../../models_and_services/common_constants/Constants';
import { DefDao } from '../../../../../models_and_services/models/dao/DefDao';


@Component({
  selector: 'app-project-e',
  standalone: true,
  imports: [CardModule, CommonModule, DropdownModule, FormsModule, InputNumberModule, ButtonModule],
  templateUrl: './project-e.component.html',
  styleUrl: './project-e.component.css'
})
export class ProjectEComponent  implements OnInit{

  constructor(
    private apiSer: ApiSerService,
    private toastrSer: ToastrSerService,
  ){}

  distinctTypes: DefDao[];
  selectedType: number;

  time_list:string[] = ["today", "yesterday", "day_before"]
  selected_time:string = "today";
  kpi:number=0;

  factory_img:string = "assets/image/data_entry/factory.png";
  java_img:string = "assets/image/data_entry/java.png";
  angular_img:string = "assets/image/data_entry/angular.png";
  python_img:string = "assets/image/data_entry/python.jpg";
  fullstack_img:string = "assets/image/data_entry/fullstack.png";

  selected_img:string;


  ngOnInit() {
    this.apiSer.getProjectTypes().subscribe(
      (response: any) => {
        this.distinctTypes = response.data.map((item: DefDao) => ({
          id: item.id,
          name: item.name
        }));
      },
      (error: any) => {
        console.error('Error fetching data', error);
      }
    );
    this.selected_img = this.factory_img
  }


  image_f(){
    if(this.selectedType==null || this.selectedType==0){
      this.selected_img = this.factory_img
    }else if(this.selectedType==1){
      this.selected_img=this.java_img;
    }
    else if(this.selectedType==2){
      this.selected_img=this.angular_img;
    }else if(this.selectedType==3){
      this.selected_img=this.python_img;
    }else if(this.selectedType==4){
      this.selected_img=this.fullstack_img;
    }
  }

  timeF(entry:string){
    if(entry=="today"){
      return 0;
    }else if(entry=="yesterday"){
      return 1;
    }else if(entry=="day_before"){
      return 2;
    }
  }
  search(){
    if(this.selectedType==null || this.selectedType == undefined || this.selected_time==null || this.selected_time==undefined
      || this.kpi<=0
    ){
      let errorMessage:string="Both sport type and timeline must be selected! Rep number can not be equal or lower than 0!";
      this.toastrSer.showError(errorMessage);
    }else{
      let data = new SaveSingleEntityDao(Constants.project, this.selectedType, this.kpi, this.timeF(this.selected_time));
      this.apiSer.saveSingleEntityF(data).subscribe(
        (response: any) => {
          let successMessage="Successfull Operation!";
          this.toastrSer.showSuccess(successMessage);
        },
        (error: any) => {
          console.error('Error fetching data', error);
        }
      );
    }
  }


  
}
