import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const HealthOrgTable = EgretLoadable({
  loader: () => import("./HealthOrgTable")
});
const ViewComponent = withTranslation()(HealthOrgTable);

const HealthOrgRoutes = [
  {
    path:  ConstantList.ROOT_PATH+"dashboard/health_org",
    exact: true,
    component: ViewComponent
  }
];

export default HealthOrgRoutes;
