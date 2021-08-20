import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const OrganizationTable = EgretLoadable({
  loader: () => import("./OrganizationTable")
});
const ViewComponent = withTranslation()(OrganizationTable);

const OrganizationRoutes = [
  {
    path:  ConstantList.ROOT_PATH+"fhir/organization",
    exact: true,
    component: ViewComponent
  }
];

export default OrganizationRoutes;
