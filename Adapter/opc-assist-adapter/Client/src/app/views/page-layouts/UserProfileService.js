import axios from "axios";
import ConstantList from "../../appConfig";
const API_PATH = ConstantList.API_ENPOINT + "/api/users/getCurrentUser";

export const getCurrentUser = ()=> {
  var url = API_PATH;
  return axios.get(url);
};


