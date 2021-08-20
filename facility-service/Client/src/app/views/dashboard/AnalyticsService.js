import axios from "axios";
import ConstantList from "../../appConfig";
const API_PATH = ConstantList.API_ENPOINT + "/api/analytics";


export const getTotalGroupByHealthOrg = (searchObject) => {
  var url = API_PATH + "/getTotalGroupByHealthOrg";
  return axios.post(url,searchObject);
};

export const getTotalRemainedCases = (searchObject) => {
  var url = API_PATH + "/getTotalRemainedCases";
  return axios.post(url,searchObject);
};
export const getTotalTestedCases = (searchObject) => {
  var url = API_PATH + "/getTotalTestedCases";
  return axios.post(url,searchObject);
};

export const getListStatus = (searchObject) => {
  var url = API_PATH + "/getListStatus";
  return axios.post(url, searchObject);
};

export const getTotalSampleByHealthOrg = (searchObject) => {
  var url = API_PATH + "/getTotalSampleByHealthOrg";
  return axios.post(url, searchObject);
}
  
  export const getTotalCases = (searchObject) => {
  var url = API_PATH + "/getTotalCases";
  return axios.post(url,searchObject);
};


