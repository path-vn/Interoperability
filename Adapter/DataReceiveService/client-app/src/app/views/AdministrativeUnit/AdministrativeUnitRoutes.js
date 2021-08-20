import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const AdministrativeUnitTable = EgretLoadable({
    loader: () =>
        import ("./AdministrativeUnitTable")
});

const ViewComponent = withTranslation()(AdministrativeUnitTable);

const administrativeUnitRoutes = [{
    path: ConstantList.ROOT_PATH + "administrative-unit",
    exact: true,
    component: ViewComponent
}];

export default administrativeUnitRoutes;