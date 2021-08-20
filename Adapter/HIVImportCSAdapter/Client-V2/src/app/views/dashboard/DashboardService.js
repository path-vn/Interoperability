import axios from "axios";
import ConstantList from "../../appConfig";

export const getDashboardAnalytics = () => {
  return axios.get(ConstantList.API_ENPOINT + "/api/dashboard/analytics");
};
