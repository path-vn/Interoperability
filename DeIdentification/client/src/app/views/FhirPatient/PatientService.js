import axios from "axios";
import ConstantList from "../../appConfig";
const API_PATH = ConstantList.API_ENPOINT + "/api/patient-fhir";

export const getAllPatient = (searchObject) => {
    let url = API_PATH + "/searchByPage";
    return axios.post(url, searchObject);
};
export const addPatient = (patient) => {
    let url = API_PATH;
    return axios.post(url, patient);
};
export const getPatient = (id) => {
    let url = API_PATH + "/" + id;
    return axios.get(url);
};
export const getPatientFromESById = (id) => {
    let url = API_PATH + "/getPatientFromESById/" + id;
    return axios.get(url);
};
export const savePatientToESById = (id) => {
    let url = API_PATH + "/save/savePatientToESById/" + id;
    return axios.get(url);
};

export const deletePatient = (id) => {
    let url = API_PATH + "/" + id;
    return axios.delete(url);
};
export const updatePatient = (patient, id) => {
    let url = API_PATH + "/" + id;
    return axios.put(url, patient);
};

export const deleteItem = (id) => {
    let url = 'http://fhir.globits.net:8082/fhir/Patient/' + id;
    return axios.delete(url);
}