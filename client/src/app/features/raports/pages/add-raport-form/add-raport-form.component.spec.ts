import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRaportFormComponent } from './add-raport-form.component';

describe('AddRaportFormComponent', () => {
  let component: AddRaportFormComponent;
  let fixture: ComponentFixture<AddRaportFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddRaportFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddRaportFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
