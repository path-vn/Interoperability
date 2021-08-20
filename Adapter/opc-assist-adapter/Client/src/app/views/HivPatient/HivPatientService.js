import axios from "axios";
import ConstantList from "../../appConfig";


export const searchByPage = object => {
    return axios.post(ConstantList.API_ENPOINT + "/public/opcassists/case/searchByDto", object);
};
export const getItemById = id => {
    return axios.get(ConstantList.API_ENPOINT + "/public/opcassists/case/assist/" + id);
};
export const addListassist = id => {
    return axios.post(ConstantList.API_ENPOINT + "/public/opcassists/case/listassist/", id);
};
export const getById = id => {
    return axios.get(ConstantList.API_ENPOINT + "/public/opcassists/case/assist/detail/" + id)
}