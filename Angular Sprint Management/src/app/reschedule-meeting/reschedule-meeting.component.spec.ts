import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RescheduleMeetingComponent } from './reschedule-meeting.component';

describe('RescheduleMeetingComponent', () => {
  let component: RescheduleMeetingComponent;
  let fixture: ComponentFixture<RescheduleMeetingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RescheduleMeetingComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RescheduleMeetingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
