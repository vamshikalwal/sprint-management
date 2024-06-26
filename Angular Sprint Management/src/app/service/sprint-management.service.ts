import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { Meetingsdto } from '../datatransferobject/meetingsdto';
import { Meetingsplatformdto } from '../datatransferobject/meetingsplatformdto';
import { Sprintsdto } from '../datatransferobject/sprintsdto';

const httpOption ={
  Headers: new Headers({ 'Content-Type': 'application/json' } )
}

@Injectable({
  providedIn: 'root'
})
export class SprintManagementService {


  meetingId !: number;

  private GenerateNewMeetingUrl = 'http://localhost:8088/api';
  private GenerateNewSprnitUrl = 'http://localhost:8088/api';
  private retriveAllSprintsUrl ="http://localhost:8088/api/sprints";
  private retriveByIdUrl ="http://localhost:8088/api/meetings";
  private retriveAllMeetingPlatformsUrl = "http://localhost:8088/api/meetings/platform";
  private rescheduleMeetingUrl ="http://localhost:8088/api/meetings";

  constructor(private http:HttpClient , private router : Router){}

  public addNewMeetingServiceMethod(meetingid:Meetingsdto)
  {
    return this.http.post<Meetingsdto>(this.GenerateNewMeetingUrl +"/meetings/new",meetingid).pipe(
      catchError(this.handleError)
    );
  }

  public  addNewSprintServiceMethod(sprint : any)
  {
    return this.http.post<Sprintsdto>(this.GenerateNewSprnitUrl +"/sprints/new",sprint).pipe(
      catchError(this.handleError)
    );
  }

  public getAllSprints()
  {
    return this.http.get<Sprintsdto[]>(this.retriveAllSprintsUrl);
  }

  public getMeetingById(meetingsdto : Meetingsdto)
  {
    this.meetingId = meetingsdto.id;
    return this.http.get<Meetingsdto>(this.retriveByIdUrl+'/'+meetingsdto.id)

  }
  
  public getAllMeetingPlatforms()
  {
    return this.http.get<Meetingsplatformdto[]>(this.retriveAllMeetingPlatformsUrl);
  }

  public rescheduleMeeting(meetingdto : Meetingsdto)
  {
    console.log("in reschedule meeting")
    return this.http.put<Meetingsdto>(this.rescheduleMeetingUrl+"/"+meetingdto.id+"/reschedule",meetingdto).pipe(
      catchError(this.handleError)
    );

  }


  private handleError(error: HttpErrorResponse) {
 
    let message = '';
 
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else{
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(`Backend returned code ${error.status}, body was: `, error.error);
      message = `${error.error.message}`;
     
    }
    // Return an observable with a user-facing error message.
    return throwError(() => new Error(message));
  }
}
