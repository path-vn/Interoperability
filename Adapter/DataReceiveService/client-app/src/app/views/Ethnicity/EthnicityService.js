import axios from "axios";
import ConstantList from "../../appConfig";
const API_PATH = ConstantList.API_ENPOINT + "/api/ethnicity";


export const searchByPage = (searchObject) => {
    var url = API_PATH + "/searchByDto";
    return axios.post(url, searchObject);
};

export const getById = (id) => {
    let url = API_PATH + "/" + id;
    return axios.get(url);
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

export const checkCode = (id, code) => {
    const config = { params: { id: id, code: code } };
    var url = API_PATH + "/checkCode";
    return axios.get(url, config);
};