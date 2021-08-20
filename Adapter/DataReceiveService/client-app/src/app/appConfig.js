const APPLICATION_PATH = "/";

module.exports = Object.freeze({
    ROOT_PATH: APPLICATION_PATH,
    ACTIVE_LAYOUT: "layout1", //layout1 = vertical, layout2=horizontal
    // API_ENPOINT: "http://csapi.globits.net:8069/receive", //online NIHE
    API_ENPOINT: "http://csapitest.globits.net:8069/receive", //online TEST
    // LOGIN_ENPOINT: "http://fhir.globits.net:8071",
    // API_ENPOINT: "http://localhost:3555/receive", //local
    LOGIN_PAGE: APPLICATION_PATH + "session/signin", //Nếu là Spring
    HOME_PAGE: APPLICATION_PATH + "dashboard/analytics", //Nếu là Spring
    //HOME_PAGE:APPLICATION_PATH+"dashboard/learning-management"//Nếu là Keycloak
    //HOME_PAGE:APPLICATION_PATH+"landing3",//Link trang landing khi bắt đầu


});