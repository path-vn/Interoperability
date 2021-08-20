import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { useTranslation, withTranslation, Trans } from 'react-i18next';
const Sample = EgretLoadable({
  loader: () => import("./Sample")
});
const ViewComponent = withTranslation()(Sample);

const SampleGroup = EgretLoadable({
  loader: () => import('./SampleGroup')
})
const ViewSampleGroupComponent = withTranslation()(SampleGroup);

const SampleRoutes = [
  {
    path: ConstantList.ROOT_PATH + "sample",
    exact: true,
    component: ViewComponent
  },
  {
    path: ConstantList.ROOT_PATH + "sample_group",
    exact: true,
    component: ViewSampleGroupComponent
  }
];

export default SampleRoutes;