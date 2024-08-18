import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SportEComponent } from './sport-e.component';

describe('SportEComponent', () => {
  let component: SportEComponent;
  let fixture: ComponentFixture<SportEComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SportEComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SportEComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
