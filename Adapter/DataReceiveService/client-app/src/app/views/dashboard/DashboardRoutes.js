import { EgretLoadable } from "egret";
import { authRoles } from "../../auth/authRoles";
import ConstantList from "../../appConfig";
import { withTranslation } from 'react-i18next';

const Analytics = EgretLoadable({
    loader: () =>
        import ("./Analytics")
});
const ViewComponent = withTranslation()(Analytics);

const dashboardRoutes = [{
    path: ConstantList.ROOT_PATH + "dashboard/analytics",
    component: ViewComponent,
    auth: authRoles.admin,
    exact: true
}, ];

export default dashboardRoutes;