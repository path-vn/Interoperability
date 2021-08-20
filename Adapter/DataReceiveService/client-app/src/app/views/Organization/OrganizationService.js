import axios from "axios";
import ConstantList from "../../appConfig";
const API_PATH = ConstantList.API_ENPOINT + "/fhir/Organization";

export const getAllOrganization = () => {
    let url = API_PATH;
    return  axios.get(url);
};
export const addOrganization = (organization) => {
    let url = API_PATH;
    return  axios.post(url,organization);
};
export const updateOrganization = (organization) => {
    let url = API_PATH+ "/" + organization.id;
    return axios.put(url,organization)
};


export const getItemById = (id) => {
    let url = API_PATH + "/" + id;
    return  axios.get(url);
};

export const deleteItem = (id) => {
    let url = API_PATH + "/" + id;
    return  axios.delete(url);
};

