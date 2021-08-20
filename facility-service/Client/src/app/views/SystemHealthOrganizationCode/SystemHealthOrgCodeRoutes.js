import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const SystemHealthOrgCode = EgretLoadable({
  loader: () => import("./SystemHealthOrgCode")
});

const ViewComponent = withTranslation()(SystemHealthOrgCode);

const systemHealthOrgCodeRoutes = [
  {
    path:  ConstantList.ROOT_PATH+"system-health-org-code",
    exact: true,
    component: ViewComponent
  }
];

export default systemHealthOrgCodeRoutes;
