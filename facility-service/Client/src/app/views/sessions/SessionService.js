import axios from "axios";
import ConstantList from "../../appConfig";
const API_PATH = ConstantList.API_ENPOINT + "/public/user/";

export const signUpAccount = item => {
  var url = API_PATH + "register";
  return axios.post(url, item);
};

export const checkuserName = item => {
  var url = API_PATH + "checkUsername";
  return axios.post(url, item);
};

export const checkEmail = item => {
  var url = API_PATH + "checkEmail";
  return axios.post(url, item);
};
