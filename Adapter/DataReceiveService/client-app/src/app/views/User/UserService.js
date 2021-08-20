import axios from "axios";
import ConstantList from "../../appConfig";
const API_PATH = ConstantList.API_ENPOINT + "/api/users/";
const API_PATH_ROLE = ConstantList.API_ENPOINT + "/api/roles/";
const API_PATH_ORG = ConstantList.API_ENPOINT + "/api/userOrganization";

export const searchByDto = (dto) => {
    var params = "/searchByDto";
    var url = API_PATH_ORG + params;
    return axios.post(url, dto);
};

export const saveUserOrg = user => {
    if (user.id) {
        return axios.put(API_PATH_ORG + "/" + user.id, user);
    } else {
        return axios.post(API_PATH_ORG, user);
    }
};

export const updateUserOrg = user => {
    return axios.post(API_PATH_ORG + user.id, user);
};

export const getUserOrgById = id => {
    var url = API_PATH_ORG + "/" + id;
    return axios.get(url);
};
export const getAllOrgByUserId = id => {
    var url = API_PATH_ORG + "/getAllOrgByUserId/" + id;
    return axios.get(url);
};

// export const getAllInfoByUserLogin = () => {
//     var url = API_PATH_ORG + "/getAllInfoByUserLogin";
//     return axios.get(url);
// };

export const getRoleUser = () => {
    var url = API_PATH_ORG + "/getRoleUser";
    return axios.get(url);
};

export const searchByPage = (page, pageSize) => {
    var params = page + "/" + pageSize;
    var url = API_PATH + params;
    return axios.get(url);
};

export const findUserByUserName = (username, page, pageSize) => {
    var params = "username/" + username + "/" + page + "/" + pageSize;
    var url = API_PATH + params;
    return axios.get(url);
};

export const getAllRoles = () => {
    var url = API_PATH_ROLE + 'all';
    return axios.get(url);
};

export const getItemById = id => {
    var url = API_PATH + id;
    return axios.get(url);
};


export const getUserByUsername = (username) => {

    const config = { params: { username: username } };
    var url = API_PATH_ORG + "/getUserByUsername";
    return axios.get(url, config);
};


export const getUserByEmail = (email) => {

    const config = { params: { email: email } };
    var url = API_PATH_ORG + "/getUserByEmail";
    return axios.get(url, config);
};
export const checkEmail = (user) => {
    return axios.post(API_PATH_ORG + "/check-email", user);
}
export const saveUser = user => {
    return axios.post(API_PATH, user);
};

//==========================================
export const getUserById = id => {
    return axios.get("/api/user", { data: id });
};
export const deleteItem = id => {
    return axios.delete(ConstantList.API_ENPOINT + "/api/user/" + id);
};
export const checkCode = (id, code) => {
    const config = { params: { id: id, code: code } };
    var url = ConstantList.API_ENPOINT + "/api/healthOrgType/checkCode";
    return axios.get(url, config);
};
export const getUserOrganizationDtoByOrgId = id => {
    var url = API_PATH_ORG + "/getUserOrganizationDtoByOrgId/" + id;
    return axios.get(url);
}

export const getCurrentUser = () => {
    var url = API_PATH + 'getCurrentUser';
    return axios.get(url);
};