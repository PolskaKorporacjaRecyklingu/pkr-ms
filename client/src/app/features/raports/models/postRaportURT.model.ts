interface ReportData {
  date: string
  shift: number;
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

export interface RaportURTPost {
  reportData: ReportData;
  forkliftOperatorsId: number[];
  leadersId: number[];
  brigadeEmployeesIdList: number[];
  refrigeratorCount: RefrigeratorCount;
  robotWork: RobotWork;
  atnWork: AtnWork;
  reportHistories: ReportHistory[];
  aggregatesWithoutOilWeights: number[];
  alCuRefrigeratorWeights: number[];
  alCuPackageIncompleteWeight: number;
  refrigeratorPowerCableWeights: number[];
  oilFromAggregatesWeights: number[];
  psAbsRefrigeratorWeights: number[];
  psAbsRefrigeratorIncompleteWeight: number;
  aluminiumWeights: number[];
  aggregatesWithOilFromWarehouseWeights: number[];
  aggregatesWithOilWeights: number[];
}
