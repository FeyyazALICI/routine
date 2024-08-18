import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectEComponent } from './project-e.component';

describe('ProjectEComponent', () => {
  let component: ProjectEComponent;
  let fixture: ComponentFixture<ProjectEComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProjectEComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectEComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
