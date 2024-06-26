import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllMeetingPlatformsComponent } from './get-all-meeting-platforms.component';

describe('GetAllMeetingPlatformsComponent', () => {
  let component: GetAllMeetingPlatformsComponent;
  let fixture: ComponentFixture<GetAllMeetingPlatformsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetAllMeetingPlatformsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetAllMeetingPlatformsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
