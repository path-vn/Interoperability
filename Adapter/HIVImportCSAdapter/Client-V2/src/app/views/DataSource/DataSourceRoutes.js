import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const Agency = EgretLoadable({
  loader: () => import("./DataSource")
});
const ViewComponent = withTranslation()(Agency);
const DataSourceRoutesRoutes = [
  {
    path: ConstantList.ROOT_PATH + "list/DataSource",
    exact: true,
    component: ViewComponent
  }
];

export default DataSourceRoutesRoutes;