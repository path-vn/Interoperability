import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const SystemAdressCode = EgretLoadable({
  loader: () => import("./SystemAdressCode")
});

const ViewComponent = withTranslation()(SystemAdressCode);

const systemAdressCodeRoutes = [
  {
    path:  ConstantList.ROOT_PATH+"system-adress-code",
    exact: true,
    component: ViewComponent
  }
];

export default systemAdressCodeRoutes;
