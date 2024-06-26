import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateNewSprintComponent } from './create-new-sprint/create-new-sprint.component';
import { HttpClientModule } from '@angular/common/http';
import { CreateNewMeetingComponent } from './create-new-meeting/create-new-meeting.component';
import { GetAllMeetingPlatformsComponent } from './get-all-meeting-platforms/get-all-meeting-platforms.component';
import { GetAllSprintsComponent } from './get-all-sprints/get-all-sprints.component';
import { GetMeetingByIdComponent } from './get-meeting-by-id/get-meeting-by-id.component';
import { RescheduleMeetingComponent } from './reschedule-meeting/reschedule-meeting.component';
import { LoginComponent } from './login/login.component';
import { AuthenticationAdminGuardService } from './service/authentication-admin-guard.service';
import { HomeComponent } from './home/home.component';

const routes: Routes = [

  { path : 'home',component : HomeComponent, canActivate:[] },
  { path : 'login',component : LoginComponent },
  { path : '', redirectTo : "login", pathMatch : "full" },

  {path:'AddNewSprint',component:CreateNewSprintComponent, canActivate:[AuthenticationAdminGuardService]},
  {path:'AddNewMeeting',component:CreateNewMeetingComponent,canActivate:[AuthenticationAdminGuardService]},
  {path:'GetAllMeetingPlatforms', component :GetAllMeetingPlatformsComponent,canActivate:[AuthenticationAdminGuardService]},
  {path:'GetAllSprints',component :GetAllSprintsComponent,canActivate:[AuthenticationAdminGuardService]},
  {path:'GetMeetingById/{id}',component :GetMeetingByIdComponent,canActivate:[AuthenticationAdminGuardService]},  
  {path: '{id}/RescheduelMeeting',component:RescheduleMeetingComponent,canActivate:[AuthenticationAdminGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
  HttpClientModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
