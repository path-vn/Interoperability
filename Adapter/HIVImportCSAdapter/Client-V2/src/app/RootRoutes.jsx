import React from "react";
import { Redirect } from "react-router-dom";
import homeRoutes from "./views/home/HomeRoutes";
import sessionRoutes from "./views/sessions/SessionRoutes";
import dashboardRoutes from "./views/dashboard/DashboardRoutes";
import administrativeUnitRoutes from "./views/AdministrativeUnit/AdministrativeUnitRoutes";
import fiscalYearRoutes from "./views/FiscalYear/FiscalYearRoutes";
import UserRoutes from "./views/User/UserRoutes";
import DataSourceRoutes from "./views/DataSource/DataSourceRoutes";
import roleRoutes from "./views/Role/RoleRoutes";
import ConstantList from "./appConfig";
import MenuRoutes from "./views/Menus/MenuRoutes";
import pageLayoutRoutes from "./views/page-layouts/PageLayoutRoutees";
import UploadFileRoutes from "./views/DataFile/UploadFileRoutes";

const redirectRoute = [
  {
    path: ConstantList.ROOT_PATH,
    exact: true,
    component: () => <Redirect to={ConstantList.HOME_PAGE} />//Luôn trỏ về HomePage được khai báo trong appConfig
  }
];

const errorRoute = [
  {
    component: () => <Redirect to={ConstantList.ROOT_PATH + "session/404"} />
  }
];

const routes = [
  ...homeRoutes,
  ...sessionRoutes,
  ...dashboardRoutes,
  ...administrativeUnitRoutes,
  ...fiscalYearRoutes,
  ...pageLayoutRoutes,
  ...UserRoutes,
  ...DataSourceRoutes,
  ...roleRoutes,
  ...MenuRoutes,
  ...UploadFileRoutes,
  ...errorRoute
];

export default routes;
