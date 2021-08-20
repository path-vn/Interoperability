import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { withTranslation } from 'react-i18next';

const User = EgretLoadable({
  loader: () => import("./User")
});

const ViewComponentUser = withTranslation()(User);

const UserProfile = EgretLoadable({
  loader: () => import("./UserProfile")
});

const ViewComponent = withTranslation()(UserProfile);

const Unit = EgretLoadable({
  loader: () => import("./Unit")
});

const ViewComponentUnit = withTranslation()(Unit);

const ChangePassword = EgretLoadable({
  loader: () => import("./ChangePassword")
});

const ViewComponentChangePassword = withTranslation()(ChangePassword);

const UserRoutes = [
  {
    path:  ConstantList.ROOT_PATH+"user-manage",
    exact: true,
    component: ViewComponentUser
  },
  {
    path:  ConstantList.ROOT_PATH+"user-profile",
    exact: true,
    component: ViewComponent
  },
  {
    path:  ConstantList.ROOT_PATH+"user-change-password",
    exact: true,
    component: ViewComponentChangePassword
  },
  {
    path:  ConstantList.ROOT_PATH+"unit-information",
    exact: true,
    component: ViewComponentUnit
  },
];

export default UserRoutes;
