import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Meetingsdto } from '../datatransferobject/meetingsdto';
import { SprintManagementService } from '../service/sprint-management.service';

@Component({
  selector: 'app-reschedule-meeting',
  templateUrl: './reschedule-meeting.component.html',
  styleUrl: './reschedule-meeting.component.css'
})
export class RescheduleMeetingComponent implements OnInit {

meetingdto : Meetingsdto = new Meetingsdto();
responseData : Boolean = false;
submitted : Boolean = false;
responseError : Boolean = false;
update : Boolean = false;

NewResponseError : Boolean =false;
NewResponseData : Boolean = false;

errorMessage !: '' ;
ifError = false;

updateValid = new FormGroup(
  {
    meetingId : new FormControl('',Validators.required)
  }
) 


constructor(private rescheduleMeeting :SprintManagementService) { }
  ngOnInit(){
  }

  onIdSubmit(){
    console.log("In on id submit");
    console.log(this.meetingdto);
    
    this.rescheduleMeeting.getMeetingById(this.meetingdto).subscribe(
      data => {
        this.meetingdto = data;
        console.log(this.meetingdto)
        this.responseData = true;
        this.NewResponseData = true;
        this.submitted = true;
      },
      error => {
        this.responseError = true;
        this.NewResponseError = true;
      }
    );
    console.log(this.submitted);
   this.submitted = true;
  }

  reload()
  {
    window.location.reload();
  }
    onUpdateFormSubmit(){
      console.log(this.meetingdto)
      this.rescheduleMeeting.rescheduleMeeting(this.meetingdto).subscribe(
        data =>{
          console.log(data); this.submitted = true;
          this.update =true;
        },
        error =>{
          this.errorMessage=error;
          this.ifError = true; this.submitted = false
        }
      )
    }



  get meetingId()
  {
    return this.updateValid.get('meetingId')
  }

  minDate()
  {
    return new Date().toISOString().split('T')[0];
  }
  
 
}




