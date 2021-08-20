import axios from "axios";
import ConstantList from "../../appConfig";
export const getAll = (pageIndex, pageSize) => {
  return axios.get(ConstantList.API_ENPOINT+"/api/case_report/get_list/"+pageIndex+"/"+pageSize);  
};

export const getById = (id) => {
  return axios.get(ConstantList.API_ENPOINT+"/api/case_report/"+ id);
}
