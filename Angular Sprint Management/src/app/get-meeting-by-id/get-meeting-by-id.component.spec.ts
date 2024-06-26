import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetMeetingByIdComponent } from './get-meeting-by-id.component';

describe('GetMeetingByIdComponent', () => {
  let component: GetMeetingByIdComponent;
  let fixture: ComponentFixture<GetMeetingByIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetMeetingByIdComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetMeetingByIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
