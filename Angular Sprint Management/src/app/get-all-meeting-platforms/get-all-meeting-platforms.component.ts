import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Meetingsplatformdto } from '../datatransferobject/meetingsplatformdto';
import { SprintManagementService } from '../service/sprint-management.service';

@Component({
  selector: 'app-get-all-meeting-platforms',
  templateUrl: './get-all-meeting-platforms.component.html',
  styleUrl: './get-all-meeting-platforms.component.css'
})
export class GetAllMeetingPlatformsComponent implements OnInit{

  meetingsplatformdto !: Meetingsplatformdto[];

  constructor (private router: Router, private meetingPlatformService : SprintManagementService){
    this.meetingPlatformService.getAllMeetingPlatforms().subscribe(data => {
      this.meetingsplatformdto = data;
    });
  }

  ngOnInit() {

  };
   
}
