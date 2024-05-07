import { Component } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {
  provideNativeDateAdapter
} from "@angular/material/core";
import {CommonModule} from "@angular/common";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import { MatCardModule} from "@angular/material/card";
import {MatButton} from "@angular/material/button";
import {ManufacturingFormComponent} from "../../components/manufacturing-form/manufacturing-form.component";

@Component({
  selector: 'app-add-raport-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatInputModule, MatFormFieldModule, MatDatepickerModule, MatSlideToggleModule, MatCardModule, MatButton, ManufacturingFormComponent],
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
    page2: this.fb.group({
      aggregatesWithoutOilWeights: this.fb.group({
        firstValue: [10],
        secondValue: [10],
      }),
      alCuRefrigeratorWeights: this.fb.group({
        firstValue: [20],
        secondValue: [20],
      }),
      alCuPackageIncompleteWeight: 200,
      refrigeratorPowerCableWeights: this.fb.group({
        firstValue: [30],
        secondValue: [30],
      }),
      oilFromAggregatesWeights: this.fb.group({
        firstValue: [40],
        secondValue: [40],
      }),
      psAbsRefrigeratorWeights: this.fb.group({
        firstValue: [50],
        secondValue: [50],
      }),
      psAbsRefrigeratorIncompleteWeight: 500,
      aluminiumWeights: this.fb.group({
        firstValue: [60],
        secondValue: [60],
      }),
      aggregatesWithOilFromWarehouseWeights: this.fb.group({
        firstValue: [50],
        secondValue: [60],
      }),
      aggregatesWithOilWeights: this.fb.group({
        firstValue: [50],
        secondValue: [60],
      }),
    })
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

  deleteBrigadeEmployee(index: number): void {
    this.brigadeEmployeesId.removeAt(index);
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

  deleteReportHistory(index: number): void {
    this.reportHistories.removeAt(index);
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
}
