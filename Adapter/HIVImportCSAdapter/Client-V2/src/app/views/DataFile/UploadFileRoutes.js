import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const UploadFile = EgretLoadable({
  loader: () => import("./UploadFile")
});
const ViewComponent = withTranslation()(UploadFile);

const UploadFileRoutes = [
  {
    path:  ConstantList.ROOT_PATH+"dashboard/upload_file",
    exact: true,
    component: ViewComponent
  }
];

export default UploadFileRoutes;