import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const DeIdentificationConfigTable = EgretLoadable({
  loader: () => import("./DeIdentificationConfigTable")
});
const ViewComponent = withTranslation()(DeIdentificationConfigTable);

const DeIdentificationConfigRoutes = [
  {
    path:  ConstantList.ROOT_PATH+"dashboard/deIdentification-config",
    exact: true,
    component: ViewComponent
  }
];

export default DeIdentificationConfigRoutes;
