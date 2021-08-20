import axios from "axios";
import ConstantList from "../../appConfig";

export const getByPage = (pageIndex,pageSize) => {
    return axios.get(ConstantList.API_ENPOINT + "/api/fiscalyear/"+pageIndex+"/"+pageSize);
  };

export const deleteItem = id => {
  return axios.delete(ConstantList.API_ENPOINT+"/api/fiscalyear/"+id);
};

export const getItemById = id => {
  return axios.get(ConstantList.API_ENPOINT + "/api/fiscalyear/" + id);
};
export const saveItem = item => {
  return axios.post(ConstantList.API_ENPOINT + "/api/fiscalyear", item);
};