import axios from "axios";
import ConstantList from "../../appConfig";
const API_PATH = ConstantList.API_ENPOINT + "/api/patient-es";
const API_PATH_FHIR = ConstantList.API_ENPOINT + "/api/patient-fhir";
const API_DELETE = ConstantList.API_ENPOINT + "/public/es-patient/patientdto/"

export const getAllPatient = (searchObject) => {
    let url = API_PATH + "/searchByPage";
    return axios.post(url, searchObject);
};
export const searchByPage = (searchObject) => {
    let url = API_PATH + "/patientdto/searchByPage";
    return axios.post(url, searchObject);
};
export const addPatient = (patient) => {
    let url = API_PATH;
    return axios.post(url, patient);
};
export const getPatient = (id) => {
    let url = API_PATH_FHIR + "/" + id;
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
    let url = API_DELETE + id;
    return axios.delete(url);
}