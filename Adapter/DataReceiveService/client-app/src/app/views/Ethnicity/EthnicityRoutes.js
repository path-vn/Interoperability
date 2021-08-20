import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const EthnicityTable = EgretLoadable({
    loader: () =>
        import ("./EthnicityTable")
});
const ViewComponent = withTranslation()(EthnicityTable);



const EthnicityRoutes = [{
    path: ConstantList.ROOT_PATH + "fhir/Ethnicity",
    exact: true,
    component: ViewComponent
}];

export default EthnicityRoutes;