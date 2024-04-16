interface ReportData {
  year: number;
  month: number;
  day: number;
  shift: {
    shift: number;
  };
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

export interface RaportURTPost {
  reportData: ReportData;
  forkliftOperatorsId: number[];
  leadersId: number[];
  brigadeEmployeesIdList: number[];
  refrigeratorCount: RefrigeratorCount;
  robotWork: RobotWork;
  atnWork: AtnWork;
}
