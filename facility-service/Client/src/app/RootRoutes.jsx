import React from "react";
import { Redirect } from "react-router-dom";
import homeRoutes from "./views/home/HomeRoutes";
import sessionRoutes from "./views/sessions/SessionRoutes";
import dashboardRoutes from "./views/dashboard/DashboardRoutes";
import UserRoutes from "./views/User/UserRoutes";
import roleRoutes from "./views/Role/RoleRoutes";
import ConstantList from "./appConfig";
import MenuRoutes from "./views/Menus/MenuRoutes";
import AdministrativeUnitRoutes from "./views/AdministrativeUnit/AdministrativeUnitRoutes";
import HealthOrganizationRoutes from "./views/HealthOrganization/HealthOrganizationRoutes";
import SystemHealthOrgCodeRoutes from "./views/SystemHealthOrganizationCode/SystemHealthOrgCodeRoutes";
import SystemAdressCode from "./views/SystemAdressCode/SystemAdressCodeRoutes";
import IsolationCenterRoutes from "./views/IsolationCenter/IsolationCenterRoutes";
import pageLayoutRoutes from "./views/page-layouts/PageLayoutRoutees";

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
  ...IsolationCenterRoutes,
  ...UserRoutes,
  ...roleRoutes,
  ...MenuRoutes,
  ...AdministrativeUnitRoutes,
  ...HealthOrganizationRoutes,
  ...SystemAdressCode,
  ...SystemHealthOrgCodeRoutes,
  ...pageLayoutRoutes,
  ...redirectRoute,
  ...errorRoute,
];

export default routes;
