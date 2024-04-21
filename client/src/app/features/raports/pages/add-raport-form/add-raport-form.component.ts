import { Component } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {
  provideNativeDateAdapter
} from "@angular/material/core";
import {addAriaReferencedId} from "@angular/cdk/a11y";
import {CommonModule} from "@angular/common";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";

@Component({
  selector: 'app-add-raport-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatInputModule, MatFormFieldModule, MatDatepickerModule, MatSlideToggleModule],
  templateUrl: './add-raport-form.component.html',
  styleUrl: './add-raport-form.component.css',
  providers: [provideNativeDateAdapter()]
})
export class AddRaportFormComponent {

  raportControl = this.fb.group({
    brigade: this.fb.group({
      date: [new Date()],
      shift: [1],
      forkliftOperatorsId: this.fb.array([this.fb.control('')]),
      leadersId: this.fb.array([this.fb.control('')]),
      brigadeEmployeesId: this.fb.array([this.fb.control('')]),
    }),
    workDone: this.fb.group({
      refrigerator: this.fb.group({
        rejected: [],
        reworked: [],
      }),
      robot: this.fb.group({
        workWith: [true],
        workHours: [0],
      }),
      atn: this.fb.group({
        workWith: [true],
        workHours: [0],
      }),
    }),
    reportHistories: this.fb.array([]),
  });
  constructor(private fb: FormBuilder) {}
  showFormData() {
    console.log(this.raportControl.value)
  }

  get leadersId() {
    return this.raportControl.get('brigade.leadersId') as FormArray
  }
  addLeader() {
    this.leadersId.push(this.fb.control(''))
  }

  get forkliftOperatorsId(): FormArray {
    return this.raportControl.get('brigade.forkliftOperatorsId') as FormArray;
  }

  // Method to add a new forklift operator control
  addForkliftOperator(): void {
    this.forkliftOperatorsId.push(this.fb.control(''));
  }

  // Getter for brigadeEmployeesId FormArray
  get brigadeEmployeesId(): FormArray {
    return this.raportControl.get('brigade.brigadeEmployeesId') as FormArray;
  }

  // Method to add a new brigade employee control
  addBrigadeEmployee(): void {
    this.brigadeEmployeesId.push(this.fb.control(''));
  }

  // Getter for reportHistories FormArray
  get reportHistories(): FormArray {
    return this.raportControl.get('reportHistories') as FormArray;
  }

  // Method to add a new report history control
  addReportHistory(): void {
    const history = this.fb.group({
      time: [new Date()],
      message: ['']
    })
    this.reportHistories.push(history);
  }

  switchRobotDisabled() {
    if(this.raportControl.get('workDone.robot.workWith')?.value) {
      this.raportControl.get('workDone.robot.workHours')?.enable();
    } else {
      this.raportControl.get('workDone.robot.workHours')?.disable();
    }
  }

  switchAtnDisabled() {
    if(this.raportControl.get('workDone.atn.workWith')?.value) {
      this.raportControl.get('workDone.atn.workHours')?.enable();
    } else {
      this.raportControl.get('workDone.atn.workHours')?.disable();
    }
  }



  //TODO: DELETE THIS
  logSwitchState() {
    console.log(!!this.raportControl.get('workDone.robot.workWith')?.value)
  }
  protected readonly addAriaReferencedId = addAriaReferencedId;
  protected readonly FormGroup = FormGroup;
}
