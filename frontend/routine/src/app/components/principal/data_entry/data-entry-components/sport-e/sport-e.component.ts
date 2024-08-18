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
  selector: 'app-sport-e',
  standalone: true,
  imports: [CardModule, CommonModule, DropdownModule, FormsModule, InputNumberModule, ButtonModule],
  templateUrl: './sport-e.component.html',
  styleUrl: './sport-e.component.css'
})
export class SportEComponent implements OnInit{

  constructor(
    private apiSer: ApiSerService,
    private toastrSer: ToastrSerService,
  ){}

  distinctTypes: DefDao[];
  selectedType: number;

  time_list:string[] = ["today", "yesterday", "day_before"]
  selected_time:string = "today";
  kpi:number=0;

  gladiator_img:string = "assets/image/data_entry/gladiator.avif";
  push_up:string = "assets/image/data_entry/push_up.png";
  squat:string = "assets/image/data_entry/squat.jpg";
  biceps:string = "assets/image/data_entry/biceps.png";
  triceps:string = "assets/image/data_entry/triceps.png";
  horse_squat:string = "assets/image/data_entry/horse_squat.webp";
  bow_draw:string = "assets/image/data_entry/bow_draw.svg";
  run:string = "assets/image/data_entry/run.jpg";
  wrist:string = "assets/image/data_entry/wrist.png";
  swim:string = "assets/image/data_entry/swim.webp";
  horsemen:string = "assets/image/data_entry/horsemen.png";
  selected_img:string;





  ngOnInit() {
    this.apiSer.getSportTypes().subscribe(
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
    this.selected_img = this.gladiator_img
  }


  image_f(){
    if(this.selectedType==null || this.selectedType==0){
      this.selected_img = this.gladiator_img
    }else if(this.selectedType==1){
      this.selected_img=this.push_up;
    }
    else if(this.selectedType==2){
      this.selected_img=this.squat;
    }else if(this.selectedType==3){
      this.selected_img=this.horse_squat;
    }else if(this.selectedType==4){
      this.selected_img=this.biceps;
    }else if(this.selectedType==5){
      this.selected_img=this.triceps;
    }else if(this.selectedType==6){
      this.selected_img=this.bow_draw;
    }else if(this.selectedType==7){
      this.selected_img=this.run;
    }else if(this.selectedType==8){
      this.selected_img=this.wrist;
    }else if(this.selectedType==9){
      this.selected_img=this.swim;
    }else if(this.selectedType==10){
      this.selected_img=this.horsemen;
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
      let data = new SaveSingleEntityDao(Constants.sport, this.selectedType, this.kpi, this.timeF(this.selected_time));
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
