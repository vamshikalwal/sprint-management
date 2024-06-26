import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Sprintsdto } from '../datatransferobject/sprintsdto';
// import { GetAllSprintsService } from '../service/get-all-sprints.service';
import { SprintManagementService } from '../service/sprint-management.service';

@Component({
  selector: 'app-get-all-sprints',
  templateUrl: './get-all-sprints.component.html',
  styleUrl: './get-all-sprints.component.css'
})
export class GetAllSprintsComponent implements OnInit{

  sprintsdto ! :Sprintsdto[];

  constructor (private router: Router, private getAllSprintsService : SprintManagementService){
    this.getAllSprintsService.getAllSprints().subscribe(data => {
      this.sprintsdto = data;
    });
  }

  ngOnInit() {

  };

}
