import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const HealthOrganization = EgretLoadable({
  loader: () => import("./HealthOrganization")
});

const ViewComponent = withTranslation()(HealthOrganization);

const healthOrganizationRoutes = [
  {
    path:  ConstantList.ROOT_PATH+"health-organization",
    exact: true,
    component: ViewComponent
  }
];

export default healthOrganizationRoutes;
