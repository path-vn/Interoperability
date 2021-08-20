import React from "react";
import { Redirect } from "react-router-dom";
import homeRoutes from "./views/home/HomeRoutes";
import sessionRoutes from "./views/sessions/SessionRoutes";
import dashboardRoutes from "./views/dashboard/DashboardRoutes";
import UserRoutes from "./views/User/UserRoutes";
import roleRoutes from "./views/Role/RoleRoutes";
import ConstantList from "./appConfig";
import MenuRoutes from "./views/Menus/MenuRoutes";
import DeIdentificationConfigRoutes from "./views/DeIdentificationConfig/DeIdentificationConfigRoutes";
import FhirPatientRoutes from "./views/FhirPatient/PatientRoutes";
import EsPatientRoutes from "./views/EsPatient/PatientRoutes";

const redirectRoute = [
  {
    path: ConstantList.ROOT_PATH,
    exact: true,
    component: () => <Redirect to={ConstantList.HOME_PAGE} />, //Luôn trỏ về HomePage được khai báo trong appConfig
  },
];

const errorRoute = [
  {
    component: () => <Redirect to={ConstantList.ROOT_PATH + "session/404"} />,
  },
];

const routes = [
  ...homeRoutes,
  ...sessionRoutes,
  ...dashboardRoutes,
  ...UserRoutes,
  ...roleRoutes,
  ...MenuRoutes,
  ...DeIdentificationConfigRoutes,
  ...FhirPatientRoutes,
  ...EsPatientRoutes,
  ...errorRoute,
];

export default routes;
