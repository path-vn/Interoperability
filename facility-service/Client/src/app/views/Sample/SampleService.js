import axios from "axios";
import ConstantList from "../../appConfig";

export const searchByPage = (sampleDto) => {
    return axios.post(ConstantList.API_ENPOINT + "/api/sample/searchByDto", sampleDto);
};

export const searchByPageDto = (sampleDto) => {
    return axios.post(ConstantList.API_ENPOINT + "/api/sample/searchByPage", sampleDto);
};

export const getItemById = id => {
    return axios.get(ConstantList.API_ENPOINT + "/api/sample/" + id);
};

export const saveItem = sample => {
    let url = ConstantList.API_ENPOINT + "/api/sample";
    if (sample.id != null) {
        return axios.put(url + "/" + sample.id, sample);
    } else {
        return axios.post(url, sample);
    }
};
export const checkCode = (id, code) => {
    const config = { params: {id: id, code: code } };
    var url = ConstantList.API_ENPOINT + "/api/sample/checkCode";
    return axios.get(url, config);
  };
export const healthOrganizationSearchByPage = (searchObject) =>{
    return axios.post(ConstantList.API_ENPOINT + "/api/healthOrg/searchByDto", searchObject);
}

export const suspectedLevelSearchByPage = (searchObject) =>{
    return axios.post(ConstantList.API_ENPOINT + "/api/suspectedLevel/searchByDto", searchObject);
}

export const suspectedTypeSearchByPage = (searchObject) =>{
    return axios.post(ConstantList.API_ENPOINT + "/api/suspectedType/searchByDto", searchObject);
}

export const epidemiologicalFactorsSearchByPage = (searchObject) =>{
    return axios.post(ConstantList.API_ENPOINT + "/api/epidemiologicalFactors/searchByDto", searchObject);
}

export const getExcel = (list) => {
    var urll = ConstantList.API_ENPOINT + "/api/downloadExcel/exportExcel";
    return axios({
      url: urll,
      method: "POST",
      responseType: "blob", // important
      data: list,
    });
  };
