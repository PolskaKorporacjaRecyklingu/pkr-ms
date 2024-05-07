interface FullName {
  firstName: string;
  lastName: string;
}

interface Employee {
  employeeId: number;
  active: boolean;
  fullName: FullName;
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

interface ReportHistory {
  time: string;
  info: string;
}

interface Weights {
  weights: number[];
  count: number;
  sumWeight?: number;
}

interface AlCuRefrigeratorWeights extends Weights {
  alCuPackageIncomplete: number;
}

interface PsAbsRefrigeratorWeights extends Weights {
  psAbsRefrigeratorIncompleteWeight: number;
}

interface ReportDate {
  date: string;
  shift: number;
}

export interface RaportURTGet {
  reportDate: ReportDate;
  leaders: Employee[];
  forkliftOperators: Employee[];
  brigade: Employee[];
  employeesCount: number;
  refrigeratorCount: RefrigeratorCount;
  robotWork: RobotWork;
  atnWork: AtnWork;
  reportHistories: ReportHistory[];
  aggregatesWithoutOilWeights: Weights;
  alCuRefrigeratorWeights: AlCuRefrigeratorWeights;
  refrigeratorPowerCableWeights: Weights;
  oilFromAggregatesWeights: Weights;
  psAbsRefrigeratorWeights: PsAbsRefrigeratorWeights;
  aggregatesWithOilWeights: Weights;
  aluminiumWeights: Weights;
  aggregatesWithOilFromWarehouseWeights: Weights;
}
