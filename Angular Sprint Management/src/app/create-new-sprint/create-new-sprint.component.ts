import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Sprintsdto } from '../datatransferobject/sprintsdto';
import { FormGroup,FormControl,Validators } from '@angular/forms';
import { SprintManagementService } from '../service/sprint-management.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-new-sprint',
  templateUrl: './create-new-sprint.component.html',
  styleUrl: './create-new-sprint.component.css'
})
export class CreateNewSprintComponent implements OnInit{
  submitted = false;
  addNewSprint : Sprintsdto = new Sprintsdto();
  errorMessage !: '' ;
 

sprintValid = new FormGroup(
  {
    projectCode : new FormControl('',Validators.required),
    startDate : new FormControl('',Validators.required),
    endDate : new FormControl('',Validators.required)
  }
) 

  constructor(private router : Router,private addNewSprintService : SprintManagementService) { }


  ngOnInit(){
  }

  reset(){
    this.submitted = false;
    
  }
  
  async save(){

    await this.addNewSprintService.addNewSprintServiceMethod
    (this.addNewSprint).subscribe(data => {console.log(data); },
    error =>{ this.errorMessage=error;
     this.submitted = false});
  }



  onSubmit(){

    if (this.sprintValid.valid)
    {
    this.submitted = true;
    this.save();

    }
   
  }



  get projectCode()
  {
    return this.sprintValid.get('projectCode')
  }
  get startDate()
  {
    return this.sprintValid.get('startDate')
  }
  get endDate()
  {
    return this.sprintValid.get('endDate')
  }

  minDate()
  {
    return new Date().toISOString().split('T')[0];
  }

}
