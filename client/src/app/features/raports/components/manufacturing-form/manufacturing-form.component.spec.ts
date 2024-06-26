import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManufacturingFormComponent } from './manufacturing-form.component';

describe('ManufacturingFormComponent', () => {
  let component: ManufacturingFormComponent;
  let fixture: ComponentFixture<ManufacturingFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManufacturingFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ManufacturingFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
