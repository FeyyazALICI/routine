import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtEComponent } from './art-e.component';

describe('ArtEComponent', () => {
  let component: ArtEComponent;
  let fixture: ComponentFixture<ArtEComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArtEComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArtEComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
