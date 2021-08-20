import axios from "axios";
import ConstantList from "../../appConfig";

const path = "/public/adapter";
export const searchByPage = object => {
    return axios.post(ConstantList.API_ENPOINT + path + "/getPage", object);
};
export const postSingleObject = id => {
    return axios.get(ConstantList.API_ENPOINT + path + "/eclinica/" + id);
};
export const addListassist = id => {
    return axios.post(ConstantList.API_ENPOINT + path + "/listeclinica/", id);
};