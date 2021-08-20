import axios from "axios";
import ConstantList from "../../appConfig";

export const getDashboardAnalytics = (searchobject) => {
    return axios.post(ConstantList.API_ENPOINT + "/api/dashboard/analytics", searchobject);
};