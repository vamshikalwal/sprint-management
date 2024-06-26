import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Meetingsdto } from '../datatransferobject/meetingsdto';
import { Meetingsplatformdto } from '../datatransferobject/meetingsplatformdto';
import { Sprintsdto } from '../datatransferobject/sprintsdto';
import { GetAllSprintsComponent } from '../get-all-sprints/get-all-sprints.component';
import { SprintManagementService } from '../service/sprint-management.service';

@Component({
  selector: 'app-create-new-meeting',
  templateUrl: './create-new-meeting.component.html',
  styleUrl: './create-new-meeting.component.css'
})
export class CreateNewMeetingComponent implements OnInit {
  formData: any;
  submitted = false;
  addNewMeeting : Meetingsdto = new Meetingsdto();
  getSprints!: Sprintsdto[];
  getAllMeetingPlatforms ! : Meetingsplatformdto[];
  errorMessage !: '' ;
  ifError = false;
  responseData : Boolean = false; 
  responseError : Boolean = false;

  message = 'Please fill in all required fields.';

  meetingValid = new FormGroup(
    {
      meetingLink : new FormControl('',Validators.required),
      // meetingDate : new FormControl('',Validators.required),
      meetingTime : new FormControl('',Validators.required),
      meetingType : new FormControl('',Validators.required), 
      sprintId : new FormControl('',Validators.required),
      meetingPassword : new FormControl('',Validators.compose([
        Validators.required,
        Validators.minLength(8)
      ])),
      // createdOn : new FormControl('',Validators.required),
      // status : new FormControl('',Validators.required),
      // updatedOn : new FormControl('',Validators.required),
      meetingPlatformId :  new FormControl('',Validators.required)

    }
  )




  constructor(private getAllSprint : SprintManagementService){}

  ngOnInit(): void {

    this.getAllSprint.getAllSprints().subscribe(
      data =>{
        // console.log(data);
        this.getSprints = data;
        console.log(this.getSprints)
      },
      error =>{
        console.log(error)
      }
    )

    this.getAllSprint.getAllMeetingPlatforms().subscribe(
      data =>{
        this.getAllMeetingPlatforms = data;
        console.log(this.getAllMeetingPlatforms)
      },
      error =>{
        console.log(error)

      }
    )
  }
  
  reload()
  {
   
  }

  reset(){
    this.submitted = false;
    
  }
  
  save()
  {
    this.getAllSprint.addNewMeetingServiceMethod(this.addNewMeeting).subscribe(data => { this.responseData = true;
      this.submitted = true;
      console.log(data)},
    error => { this.errorMessage=error;
      this.ifError = true; this.submitted = false}
    );
    console.log("Im in meeting save")
  }

  onSubmit(){
    console.log("In submit")
    this.submitted = true;
    this.save();
    console.log(this.addNewMeeting)
    this.message = 'Meeting data submitted successfully!';
  }

  get meetingLink()
  {
    return this.meetingValid.get('meetingLink')
  }

  get meetingTime()
  {
    return this.meetingValid.get('meetingTime')
  }
  
  get meetingType()
  {
    return this.meetingValid.get('meetingType')
  }

  get sprintId()
  {
    return this.meetingValid.get('sprintId')
  }


  get meetingPassword()
  {
    return this.meetingValid.get('meetingPassword')
  }

  get meetingPlatformId()
  {
    return this.meetingValid.get('meetingPlatformId')
  }




}
