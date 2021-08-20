import axios from "axios";
import ConstantList from "../../appConfig";
const API_PATH = ConstantList.API_ENPOINT + "/api/users/";
const API_PATH_ROLE = ConstantList.API_ENPOINT + "/api/roles/";

export const searchByPage = (page, pageSize) => {
  var params = page + "/" + pageSize;
  var url = API_PATH + params;
  return axios.get(url);
};

export const findUserByUserName = (username, page, pageSize) => {
  var params = "username/" + username + "/" + page + "/" + pageSize;
  var url = API_PATH + params;
  return axios.get(url);
};

export const getAllRoles = () => {
  var url = API_PATH_ROLE + 'all';
  return axios.get(url);
};

export const getItemById = id => {
  var url = API_PATH + id;
  return axios.get(url);
};


export const getUserByUsername = (username) => {
  const config = { params: { username: username} };
  var url = API_PATH;
  return axios.get(url, config);
};

export const saveUser = user => {
  return axios.post(API_PATH, user);
};

//==========================================
export const getUserById = id => {
  return axios.get("/api/user", { data: id });
};
export const deleteItem = id => {
  return axios.delete(ConstantList.API_ENPOINT + "/api/healthOrgType/" + id);
};
export const checkCode = (id, code) => {
  const config = { params: { id: id, code: code } };
  var url = ConstantList.API_ENPOINT + "/api/healthOrgType/checkCode";
  return axios.get(url, config);
};