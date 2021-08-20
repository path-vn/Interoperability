import axios from "axios";
import ConstantList from "../../appConfig";
const API_PATH = ConstantList.API_ENPOINT + "/api/healthOrg";


export const searchByPage = (searchObject) => {
    var url = API_PATH + "/searchByDto";
    return axios.post(url, searchObject);
};

export const getOrgByType = (searchObject) => {
    var url = API_PATH + "/getOrgByType";
    return axios.post(url, searchObject);
};

export const getOrgByTypes = (searchObject) => {
    var url = API_PATH + "/getOrgByTypes";
    return axios.post(url, searchObject);
};

export const findAllChildHealthOrganizationByUserLogin = (searchObject) => {
    var url = API_PATH + "/findAllChildHealthOrganizationByUserLogin";
    return axios.post(url, searchObject);
};

export const deleteItem = (id) => {
    let url = API_PATH + "/" + id;
    return axios.delete(url);
};

export const addNew = (obj) => {
    let url = API_PATH;
    return axios.post(url, obj);
};

export const update = (obj) => {
    let url = API_PATH + "/" + obj.id;
    return axios.put(url, obj);
};

export const getOne = (id) => {
    let url = API_PATH + "/" + id;
    return axios.get(url);
};

export const checkCode = (id, code) => {
    const config = { params: { id: id, code: code } };
    var url = ConstantList.API_ENPOINT + "/api/healthOrg/checkCode";
    return axios.get(url, config);
};
export const getAll = () => {
    let url = API_PATH + "/getAll";
    return axios.get(url);
};