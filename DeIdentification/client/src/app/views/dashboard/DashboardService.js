import axios from "axios";
import ConstantList from "../../appConfig";

export const getDashboardAnalytics = (object) => {
  return axios.post(ConstantList.API_ENPOINT + "/api/dashboard/analytics/getAnalytics",object);
};
