import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const HivPatient = EgretLoadable({
  loader: () => import("./HivPatient")
});
const ViewComponent = withTranslation()(HivPatient);

const HivPatientRoutes = [
  {
    path:  ConstantList.ROOT_PATH+"dashboard/patient",
    exact: true,
    component: ViewComponent
  }
];

export default HivPatientRoutes;