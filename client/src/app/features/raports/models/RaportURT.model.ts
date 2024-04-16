import {Injectable} from "@angular/core";
import {Adapter} from "../../../core/adapters/adapter";
import {RaportURTGet} from "./getRaportURT.model";
import {RaportURTPost} from "./postRaportURT.model";

export class Employee {
  constructor(
    public id: number,
    public active: boolean|null,
    public firstName: string|null,
    public nastName: string|null
  ) {}
}
export class RaportUrt {
  constructor(
    public date: Date ,
    public shift: number,
    public forkliftOperators: Employee[],
    public leaders: Employee[],
    public brigadeEmployees: Employee[],
    public refrigeratorCount: {
      rejected: number,
      reworked: number
    },
    public robotWork: {
      worked: boolean,
      hours: number
    },
    public atnWork: {
      worked: boolean,
      hours: number
    }) {}
}

@Injectable({
  providedIn: 'root'
})
export class RaportUrtDTOToRaportUrtAdapter implements Adapter<RaportUrt> {
  adapt(item: RaportURTGet): RaportUrt {
    let dateTime = new Date(item.reportData.year, item.reportData.month - 1, item.reportData.day)
    let leaders = item.leaders.map(leader => {
      return new Employee(leader.employeeId, leader.active, leader.fullName.firstName, leader.fullName.lastName)
    })
    let forkliftOperators = item.forkliftOperators.map(leader => {
      return new Employee(leader.employeeId, leader.active, leader.fullName.firstName, leader.fullName.lastName)
    })
    let brigade = item.brigade.map(leader => {
      return new Employee(leader.employeeId, leader.active, leader.fullName.firstName, leader.fullName.lastName)
    })
    let refrigeratorCount = {
      rejected: item.refrigeratorCount.rejectedRefrigerators,
      reworked: item.refrigeratorCount.reworkedRefrigerators
    }
    let robotWork =  {
      worked: item.robotWork.workWithRobot,
      hours: item.robotWork.workWithRobotHours
    }
    let atnWork =  {
      worked: item.atnWork.workWithAtn,
      hours: item.atnWork.workWithAtnHours
    }
    return new RaportUrt(dateTime, item.reportData.shift.shift, leaders, forkliftOperators, brigade, refrigeratorCount, robotWork, atnWork)

  }
}

@Injectable({
  providedIn: 'root'
})
export class RaportUrtToRaportUrtDTOAdapter implements Adapter<RaportURTPost> {

  adapt(item: RaportUrt): RaportURTPost {
    let reportData = {
      year: item.date.getFullYear(),
      month: item.date.getFullYear()+1,
      day: item.date.getDay(),
      shift: {
        shift: item.shift
      }
    }
    let forkliftOperatorsId = item.forkliftOperators.map(operator => operator.id)
    let leadersId = item.leaders.map(operator => operator.id)
    let brigadeEmployeesIdList = item.brigadeEmployees.map(operator => operator.id)
    let refrigeratorCount = {
      rejectedRefrigerators: item.refrigeratorCount.rejected,
      reworkedRefrigerators: item.refrigeratorCount.reworked
    }
    let robotWork = {
      workWithRobot: item.robotWork.worked,
      workWithRobotHours: item.robotWork.hours
    }
    let atnWork = {
      workWithAtn: item.atnWork.worked,
      workWithAtnHours: item.atnWork.hours
    }
    return {
      reportData,
      forkliftOperatorsId,
      leadersId,
      brigadeEmployeesIdList,
      refrigeratorCount,
      robotWork,
      atnWork
    }
  }
}
