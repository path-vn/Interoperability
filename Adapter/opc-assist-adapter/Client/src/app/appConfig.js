const APPLICATION_PATH = "/";

//const APPLICATION_PATH="/asset_develop/";//Đặt homepage tại package.json giống như tại đây nếu deploy develop
module.exports = Object.freeze({
    //ROOT_PATH : "/egret/",
    ROOT_PATH: APPLICATION_PATH,
    ACTIVE_LAYOUT: "layout1", //layout1 = vertical, layout2=horizontal
    API_ENPOINT: "http://localhost:3444/opcassistadapter", //local
    // API_ENPOINT: "http://opcadapter.globits.net:3444/opcassistadapter", //online
    // LOGIN_PAGE: APPLICATION_PATH + "session/signin",//Nếu là Spring
    LOGIN_PAGE: APPLICATION_PATH + "dashboard/analytics", //Nếu là Spring
    HOME_PAGE: APPLICATION_PATH + "dashboard/analytics", //Nếu là Spring
    //HOME_PAGE:APPLICATION_PATH+"dashboard/learning-management"//Nếu là Keycloak
    //HOME_PAGE:APPLICATION_PATH+"landing3",//Link trang landing khi bắt đầu
    MATERIAL_DEPARTMENT_CODE: "VPB4",

});