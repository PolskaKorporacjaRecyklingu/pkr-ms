import {Injectable} from "@angular/core";
import {Adapter} from "../../../core/adapters/adapter";
import {RaportURTGet} from "./getRaportURT.model";
import {RaportURTPost} from "./postRaportURT.model";
export class FullName {
  constructor(
    public firstName: string,
    public lastName: string
  ) {}
}

export class Employee {
  constructor(
    public employeeId: number,
    public active: boolean,
    public fullName: FullName
  ) {}
}

export class RefrigeratorCount {
  constructor(
    public rejectedRefrigerators: number,
    public reworkedRefrigerators: number
  ) {}
}

export class WorkHours {
  constructor(
    public work: boolean,
    public hours: number
  ) {}
}

export class ReportHistory {
  constructor(
    public time: string,
    public info: string
  ) {}
}

export class Weights {
  constructor(
    public weights: number[],
    public count: number,
    public sumWeight?: number
  ) {}
}

export class AlCuRefrigeratorWeights extends Weights {
  constructor(
    weights: number[],
    count: number,
    public alCuPackageIncomplete: number,
    sumWeight?: number
  ) {
    super(weights, count, sumWeight);
  }
}

export class PsAbsRefrigeratorWeights extends Weights {
  constructor(
    weights: number[],
    count: number,
    public psAbsRefrigeratorIncompleteWeight: number,
    sumWeight?: number
  ) {
    super(weights, count, sumWeight);
  }
}

export class ReportDate {
  constructor(
    public date: string,
    public shift: number
  ) {}
}

export class RaportURT {
  constructor(
    public reportDate: ReportDate,
    public leaders: Employee[],
    public forkliftOperators: Employee[],
    public brigade: Employee[],
    public employeesCount: number,
    public refrigeratorCount: RefrigeratorCount,
    public robotWork: WorkHours,
    public atnWork: WorkHours,
    public reportHistories: ReportHistory[],
    public aggregatesWithoutOilWeights: Weights,
    public alCuRefrigeratorWeights: AlCuRefrigeratorWeights,
    public refrigeratorPowerCableWeights: Weights,
    public oilFromAggregatesWeights: Weights,
    public psAbsRefrigeratorWeights: PsAbsRefrigeratorWeights,
    public aggregatesWithOilWeights: Weights,
    public aluminiumWeights: Weights,
    public aggregatesWithOilFromWarehouseWeights: Weights
  ) {}
}

@Injectable({
  providedIn: 'root'
})
export class GetRaportUrtToRaportUrt implements Adapter<RaportURT> {
  adapt(getRaportURT: RaportURTGet): RaportURT {
    return new RaportURT(
      new ReportDate(getRaportURT.reportDate.date, getRaportURT.reportDate.shift),
      getRaportURT.leaders.map(leader => new Employee(leader.employeeId, leader.active, leader.fullName)),
      getRaportURT.forkliftOperators.map(operator => new Employee(operator.employeeId, operator.active, operator.fullName)),
      getRaportURT.brigade.map(employee => new Employee(employee.employeeId, employee.active, employee.fullName)),
      getRaportURT.employeesCount,
      new RefrigeratorCount(getRaportURT.refrigeratorCount.rejectedRefrigerators, getRaportURT.refrigeratorCount.reworkedRefrigerators),
      new WorkHours(getRaportURT.robotWork.workWithRobot, getRaportURT.robotWork.workWithRobotHours),
      new WorkHours(getRaportURT.atnWork.workWithAtn, getRaportURT.atnWork.workWithAtnHours),
      getRaportURT.reportHistories.map(history => new ReportHistory(history.time, history.info)),
      new Weights(getRaportURT.aggregatesWithoutOilWeights.weights, getRaportURT.aggregatesWithoutOilWeights.count, getRaportURT.aggregatesWithoutOilWeights.sumWeight),
      new AlCuRefrigeratorWeights(getRaportURT.alCuRefrigeratorWeights.weights, getRaportURT.alCuRefrigeratorWeights.count, getRaportURT.alCuRefrigeratorWeights.alCuPackageIncomplete, getRaportURT.alCuRefrigeratorWeights.sumWeight),
      new Weights(getRaportURT.refrigeratorPowerCableWeights.weights, getRaportURT.refrigeratorPowerCableWeights.count),
      new Weights(getRaportURT.oilFromAggregatesWeights.weights, getRaportURT.oilFromAggregatesWeights.count),
      new PsAbsRefrigeratorWeights(getRaportURT.psAbsRefrigeratorWeights.weights, getRaportURT.psAbsRefrigeratorWeights.count, getRaportURT.psAbsRefrigeratorWeights.psAbsRefrigeratorIncompleteWeight, getRaportURT.psAbsRefrigeratorWeights.sumWeight),
      new Weights(getRaportURT.aggregatesWithOilWeights.weights, getRaportURT.aggregatesWithOilWeights.count, getRaportURT.aggregatesWithOilWeights.sumWeight),
      new Weights(getRaportURT.aluminiumWeights.weights, getRaportURT.aluminiumWeights.count),
      new Weights(getRaportURT.aggregatesWithOilFromWarehouseWeights.weights, getRaportURT.aggregatesWithOilFromWarehouseWeights.count, getRaportURT.aggregatesWithOilFromWarehouseWeights.sumWeight)
    );
  }
}


@Injectable({
  providedIn: 'root'
})
export class RaportUrtToPostRaportUrt implements Adapter<RaportURTPost> {
  adapt(raportUrt: RaportURT): RaportURTPost {
    return {
      reportData: { date: raportUrt.reportDate.date, shift: raportUrt.reportDate.shift },
      forkliftOperatorsId: raportUrt.forkliftOperators.map(operator => operator.employeeId),
      leadersId: raportUrt.leaders.map(leader => leader.employeeId),
      brigadeEmployeesIdList: raportUrt.brigade.map(employee => employee.employeeId),
      refrigeratorCount: { rejectedRefrigerators: raportUrt.refrigeratorCount.rejectedRefrigerators, reworkedRefrigerators: raportUrt.refrigeratorCount.reworkedRefrigerators },
      robotWork: { workWithRobot: raportUrt.robotWork.work, workWithRobotHours: raportUrt.robotWork.hours },
      atnWork: { workWithAtn: raportUrt.atnWork.work, workWithAtnHours: raportUrt.atnWork.hours },
      reportHistories: raportUrt.reportHistories.map(history => ({ time: history.time, info: history.info })),
      aggregatesWithoutOilWeights: raportUrt.aggregatesWithoutOilWeights.weights,
      alCuRefrigeratorWeights: raportUrt.alCuRefrigeratorWeights.weights,
      alCuPackageIncompleteWeight: raportUrt.alCuRefrigeratorWeights.alCuPackageIncomplete,
      refrigeratorPowerCableWeights: raportUrt.refrigeratorPowerCableWeights.weights,
      oilFromAggregatesWeights: raportUrt.oilFromAggregatesWeights.weights,
      psAbsRefrigeratorWeights: raportUrt.psAbsRefrigeratorWeights.weights,
      psAbsRefrigeratorIncompleteWeight: raportUrt.psAbsRefrigeratorWeights.psAbsRefrigeratorIncompleteWeight,
      aluminiumWeights: raportUrt.aluminiumWeights.weights,
      aggregatesWithOilFromWarehouseWeights: raportUrt.aggregatesWithOilFromWarehouseWeights.weights,
      aggregatesWithOilWeights: raportUrt.aggregatesWithOilWeights.weights
    };
  }
}

// @Injectable({
//   providedIn: 'root'
// })
// export class RaportUrtDTOToRaportUrtAdapter implements Adapter<RaportUrt> {
//   adapt(item: RaportURTGet): RaportUrt {
//     let dateTime = new Date(item.reportData.year, item.reportData.month - 1, item.reportData.day)
//     let leaders = item.leaders.map(leader => {
//       return new EmployeeAdapter(leader.employeeId, leader.active, leader.fullName.firstName, leader.fullName.lastName)
//     })
//     let forkliftOperators = item.forkliftOperators.map(leader => {
//       return new EmployeeAdapter(leader.employeeId, leader.active, leader.fullName.firstName, leader.fullName.lastName)
//     })
//     let brigade = item.brigade.map(leader => {
//       return new EmployeeAdapter(leader.employeeId, leader.active, leader.fullName.firstName, leader.fullName.lastName)
//     })
//     let refrigeratorCount = {
//       rejected: item.refrigeratorCount.rejectedRefrigerators,
//       reworked: item.refrigeratorCount.reworkedRefrigerators
//     }
//     let robotWork =  {
//       worked: item.robotWork.workWithRobot,
//       hours: item.robotWork.workWithRobotHours
//     }
//     let atnWork =  {
//       worked: item.atnWork.workWithAtn,
//       hours: item.atnWork.workWithAtnHours
//     }
//     return new RaportUrt(dateTime, item.reportData.shift.shift, leaders, forkliftOperators, brigade, refrigeratorCount, robotWork, atnWork)
//
//   }
// }

// @Injectable({
//   providedIn: 'root'
// })
// export class RaportUrtToRaportUrtDTOAdapter implements Adapter<RaportURTPost> {
//
//   adapt(item: RaportUrt): RaportURTPost {
//     let dateStr = item.date.toISOString()
//     let dateOnly = dateStr.split("T")[0]
//     let reportData = {
//       date: dateOnly,
//       shift: item.shift
//     }
//     let forkliftOperatorsId = item.forkliftOperators.map(operator => operator.id)
//     let leadersId = item.leaders.map(operator => operator.id)
//     let brigadeEmployeesIdList = item.brigadeEmployees.map(operator => operator.id)
//     let refrigeratorCount = {
//       rejectedRefrigerators: item.refrigeratorCount.rejected,
//       reworkedRefrigerators: item.refrigeratorCount.reworked
//     }
//     let robotWork = {
//       workWithRobot: item.robotWork.worked,
//       workWithRobotHours: item.robotWork.hours
//     }
//     let atnWork = {
//       workWithAtn: item.atnWork.worked,
//       workWithAtnHours: item.atnWork.hours
//     }
//     return {
//       reportData,
//       forkliftOperatorsId,
//       leadersId,
//       brigadeEmployeesIdList,
//       refrigeratorCount,
//       robotWork,
//       atnWork
//     }
//   }
// }
