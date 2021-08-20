import axios from "axios";
import ConstantList from "../../appConfig";


export const searchByPage = dto => {
    return axios.post(ConstantList.API_ENPOINT + "/api/deIdentificationConfig/searchByPage", dto);
};


export const deleteItem = id => {
    return axios.delete(ConstantList.API_ENPOINT + "/api/deIdentificationConfig/" + id);
};

export const getById = id => {
    return axios.get(ConstantList.API_ENPOINT + "/api/deIdentificationConfig/" + id);
};
export const checkCode = (id, code) => {
    const config = { params: { id: id, code: code } };
    var url = ConstantList.API_ENPOINT + "/api/deIdentificationConfig/checkCode";
    return axios.get(url, config);
};

export const addNew = dto => {
    return axios.post(ConstantList.API_ENPOINT + "/api/deIdentificationConfig", dto);
};
export const update = dto => {
    return axios.put(ConstantList.API_ENPOINT + "/api/deIdentificationConfig/" + dto.id, dto);
};