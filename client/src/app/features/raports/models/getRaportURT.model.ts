interface FullName {
  firstName: string;
  lastName: string;
}

interface Employee {
  employeeId: number;
  active: boolean;
  fullName: FullName;
}

interface Shift {
  shift: number;
}

interface ReportData {
  year: number;
  month: number;
  day: number;
  shift: Shift;
}

interface EmployeesCount {
  employeesCount: number;
}

interface RefrigeratorCount {
  rejectedRefrigerators: number;
  reworkedRefrigerators: number;
}

interface RobotWork {
  workWithRobot: boolean;
  workWithRobotHours: number;
}

interface AtnWork {
  workWithAtn: boolean;
  workWithAtnHours: number;
}

export interface RaportURTGet {
  reportData: ReportData;
  leaders: Employee[];
  forkliftOperators: Employee[];
  brigade: Employee[];
  employeesCount: EmployeesCount;
  refrigeratorCount: RefrigeratorCount;
  robotWork: RobotWork;
  atnWork: AtnWork;
}
