import axios from "axios";
import ConstantList from "../../appConfig";


export const searchByPage = object => {
    return axios.post(ConstantList.API_ENPOINT + "/api/dataFile/searchByDto", object);
  };

  export const getDataSource = object => {
    return axios.post(ConstantList.API_ENPOINT + "/api/dataSource/searchByDto", object);
  };

export const deleteItem = id => {
  return axios.delete(ConstantList.API_ENPOINT+"/api/dataFile/"+id);
};

export const getItemById = id => {
  return axios.get(ConstantList.API_ENPOINT + "/api/dataFile/" + id);
};


export const addNew = eQAPlanning => {
  return axios.post(ConstantList.API_ENPOINT + "/api/dataFile", eQAPlanning);
};
export const update = eQAPlanning => {
  return axios.put(ConstantList.API_ENPOINT + "/api/dataFile/" + eQAPlanning.id, eQAPlanning);
};
export const getListData = object => {
    return axios.post(ConstantList.API_ENPOINT + "/api/dataFile/searchByDto", object);
  };
export const getFile = (id) => {
    return axios.post(ConstantList.API_ENPOINT + "/api/uploadExcel/getFile" +"/"+id);
}; 
export const exportToExcel = (id) => {
  return axios({
    method: 'get',
    url: ConstantList.API_ENPOINT + "/api/downloadExcel" +"/"+id,
    responseType: 'blob',
  })
};
export const exportToExcelElog = () => {
  return axios({
    method: 'get',
    url: ConstantList.API_ENPOINT + "/api/downloadExcel" +"/elog",
    responseType: 'blob',
  })
};