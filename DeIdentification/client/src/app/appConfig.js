const APPLICATION_PATH = "/";
module.exports = Object.freeze({
    //ROOT_PATH : "/egret/",
    ROOT_PATH: APPLICATION_PATH,
    ACTIVE_LAYOUT: "layout1", //layout1 = vertical, layout2=horizontal
    // API_ENPOINT: "http://deidencs.globits.net:8993/deidentification", //online nihe
    API_ENPOINT: "http://deiden.globits.net:8993/deidentification", //online 313
    // API_ENPOINT: "http://localhost:8993/deidentification", //local
    // API_ENPOINT: "http://api.oceantech.vn/vitd",
    LOGIN_PAGE: APPLICATION_PATH + "session/signin", //Nếu là Spring
    HOME_PAGE: APPLICATION_PATH + "dashboard/analytics", //Nếu là Spring
    //HOME_PAGE:APPLICATION_PATH+"dashboard/learning-management"//Nếu là Keycloak
    //HOME_PAGE:APPLICATION_PATH+"landing3",//Link trang landing khi bắt đầu
    MATERIAL_DEPARTMENT_CODE: "VPB4",

});