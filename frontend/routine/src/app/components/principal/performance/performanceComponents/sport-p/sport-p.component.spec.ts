import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SportPComponent } from './sport-p.component';

describe('SportPComponent', () => {
  let component: SportPComponent;
  let fixture: ComponentFixture<SportPComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SportPComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SportPComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
