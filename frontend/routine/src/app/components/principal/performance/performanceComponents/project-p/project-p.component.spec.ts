import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectPComponent } from './project-p.component';

describe('ProjectPComponent', () => {
  let component: ProjectPComponent;
  let fixture: ComponentFixture<ProjectPComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProjectPComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectPComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
