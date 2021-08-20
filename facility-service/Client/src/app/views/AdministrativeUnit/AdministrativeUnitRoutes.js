import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const AdministrativeUnit = EgretLoadable({
  loader: () => import("./AdministrativeUnit")
});

const ViewComponent = withTranslation()(AdministrativeUnit);

const administrativeUnitRoutes = [
  {
    path:  ConstantList.ROOT_PATH+"administrative-unit",
    exact: true,
    component: ViewComponent
  }
];

export default administrativeUnitRoutes;
