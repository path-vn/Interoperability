import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const IsolationCenter = EgretLoadable({
  loader: () => import("./IsolationCenter")
});
const ViewComponent = withTranslation()(IsolationCenter);

const IsolationCenterRoutes = [
  {
    path:  ConstantList.ROOT_PATH+"dashboard/isolation-center",
    exact: true,
    component: ViewComponent
  }
];

export default IsolationCenterRoutes;