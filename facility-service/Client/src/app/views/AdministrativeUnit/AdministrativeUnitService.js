import axios from "axios";
import ConstantList from "../../appConfig";

var path = "/api/facilityAdministrativeUnit";
export const getAllAdministrativeUnits = () => {
  return axios.get(ConstantList.API_ENPOINT + path + "1/10");
};

export const getByPage = (searchDto) => {
  var url = ConstantList.API_ENPOINT + path + "/searchByDto" ;
  return axios.post(url, searchDto);
};

export const getByPageByParentId = (searchDto) => {
  if (searchDto.parentId != null || searchDto.isGetAllCity) {
    var url = ConstantList.API_ENPOINT + path + "/searchByDto" ;
    return axios.post(url, searchDto);
  }
  searchDto.pageSize = 0;
  return axios.post(url, searchDto);
};

export const checkCode = (id, code) => {
  const config = { params: {id: id, code: code } };
  var url = ConstantList.API_ENPOINT + path+"/checkCode";
  return axios.get(url, config);
};

export const getListBasic = () => {
  var url = ConstantList.API_ENPOINT + path + "/getListBasic" ;
  return axios.get(url);
};

export const getById = (id) => {
  var url = ConstantList.API_ENPOINT + path +  "/" + id;
  return axios.get(url);
};
export const deleteAdministrativeUnit = id => {
  return axios.delete(ConstantList.API_ENPOINT + path + "/" + id);
};
export const addNewAdministrativeUnit = adminUnit => {
  return axios.post(ConstantList.API_ENPOINT + path, adminUnit);
};
export const updateAdministrativeUnit = (adminUnit, id) => {
  return axios.put(ConstantList.API_ENPOINT + path + "/" + id, adminUnit, id);
};