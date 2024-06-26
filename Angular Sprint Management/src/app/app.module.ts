import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GetAllMeetingPlatformsComponent } from './get-all-meeting-platforms/get-all-meeting-platforms.component';
import { CreateNewSprintComponent } from './create-new-sprint/create-new-sprint.component';
import { NavigationComponent } from './navigation/navigation.component';
import { CreateNewMeetingComponent } from './create-new-meeting/create-new-meeting.component';
import { GetAllSprintsComponent } from './get-all-sprints/get-all-sprints.component';
import { GetMeetingByIdComponent } from './get-meeting-by-id/get-meeting-by-id.component';
import { RescheduleMeetingComponent } from './reschedule-meeting/reschedule-meeting.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    GetAllMeetingPlatformsComponent,
    CreateNewSprintComponent,
    NavigationComponent,
    CreateNewMeetingComponent,
    GetAllSprintsComponent,
    GetMeetingByIdComponent,
    RescheduleMeetingComponent,
    LoginComponent,
    LogoutComponent,
    HomeComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
