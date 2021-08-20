import axios from "axios";
import ConstantList from "../../appConfig";

const API_PATH = ConstantList.API_ENPOINT + "/api/dataSource/";

export const searchByPage = (searchObject) => {
    return axios.post(API_PATH + "searchByDto", searchObject);
};

export const deleteById = id => {
    return axios.delete(API_PATH + id);
};

export const addNew = item => {
    return axios.post(API_PATH, item);
};
export const update = item => {
    return axios.put(API_PATH + item.id, item);
};

export const getById = id => {
    return axios.get(API_PATH + id);
};

export const checkCode = (id, code) => {
    const config = { params: { id: id, code: code } };
    return axios.get(API_PATH + "checkCode", config);
};

export const checkUrl = (id, channelUrl) => {
    const config = { params: { id: id, channelUrl: channelUrl } };
    return axios.get(API_PATH + "checkUrl", config);
};