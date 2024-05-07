import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowRaportComponent } from './show-raport.component';

describe('ShowRaportComponent', () => {
  let component: ShowRaportComponent;
  let fixture: ComponentFixture<ShowRaportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowRaportComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ShowRaportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
