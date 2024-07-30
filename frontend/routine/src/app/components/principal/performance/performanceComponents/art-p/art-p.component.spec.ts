import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtPComponent } from './art-p.component';

describe('ArtPComponent', () => {
  let component: ArtPComponent;
  let fixture: ComponentFixture<ArtPComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArtPComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArtPComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
