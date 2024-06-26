import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllSprintsComponent } from './get-all-sprints.component';

describe('GetAllSprintsComponent', () => {
  let component: GetAllSprintsComponent;
  let fixture: ComponentFixture<GetAllSprintsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetAllSprintsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetAllSprintsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
