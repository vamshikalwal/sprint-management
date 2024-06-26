import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Meetingsdto } from '../datatransferobject/meetingsdto';
import { SprintManagementService } from '../service/sprint-management.service';

@Component({
  selector: 'app-get-meeting-by-id',
  templateUrl: './get-meeting-by-id.component.html',
  styleUrl: './get-meeting-by-id.component.css'
})

export class GetMeetingByIdComponent  implements OnInit{

  meetingsDtoObject : Meetingsdto = new Meetingsdto();
  meetingsdto !: Meetingsdto[] ;
  submitted = false;
  noRecordFound=false;

  responseData : Boolean = false; 
  responseError : Boolean = false;

 meetingDtoObject1: Meetingsdto= new Meetingsdto();
  constructor(private router: Router, private getMeetingByIdService: SprintManagementService)
  {}

  getByIdValid = new FormGroup({
    meetingById : new FormControl('',Validators.required)
  })
  
ngOnInit(){}

  search() {
    this.getMeetingByIdService.getMeetingById(this.meetingsDtoObject)
    .subscribe( data => {
      this.responseData = true;
      this.meetingDtoObject1 = data;

      if(this.meetingDtoObject1.id===0){
        console.log("in if");
        this.noRecordFound=true;
      }
    },error =>{
       this.responseError = true;
    });
}
onSubmit(){
  this.submitted = true;
  this.search();

 }
 


 get meetingById()
 {
  return this.getByIdValid.get('meetingById')
 }

}


