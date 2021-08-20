import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const PatientTable = EgretLoadable({
    loader: () =>
        import ("./PatientTable")
});
const ViewComponent = withTranslation()(PatientTable);

const PatientRoutes = [{
    path: ConstantList.ROOT_PATH + "fhir/EsPatient",
    exact: true,
    component: ViewComponent
}];

export default PatientRoutes;