import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const HivCaseReportTable = EgretLoadable({
    loader: () =>
        import ("./HivCaseReportTable")
});

const ViewComponent = withTranslation()(HivCaseReportTable);

const hivCaseReportRoutes = [{
    path: ConstantList.ROOT_PATH + "hiv-case-report",
    exact: true,
    component: ViewComponent
}];

export default hivCaseReportRoutes;