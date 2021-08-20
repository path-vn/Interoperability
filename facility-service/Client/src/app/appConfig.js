const APPLICATION_PATH = "/";
module.exports = Object.freeze({
  //ROOT_PATH : "/egret/",
  ROOT_PATH: APPLICATION_PATH,
  SYSTEM_TYPE: [
    {
      index: 0,
      key: "HivInfo",
      name : "HivInfo"
    },
    {
      index: 1,
      key: "OpcAssist",
      name : "OpcAssist"
    },
    {
      index: 2,
      key: "OpcEclinica",
      name : "OpcEclinica"
    },
    {
      index: 3,
      key: "HtcElog",
      name : "HtcElog"
    },
    {
      index: 4,
      key: "Pdma",
      name : "Pdma"
    },
  ],
  ACTIVE_LAYOUT: "layout1",//layout1 = vertical, layout2=horizontal
  API_ENPOINT: "http://localhost:8095/facility",    //local
  LOGIN_PAGE: APPLICATION_PATH + "session/signin",//Nếu là Spring
  HOME_PAGE: APPLICATION_PATH + "dashboard/analytics",//Nếu là Spring
  //HOME_PAGE:APPLICATION_PATH+"dashboard/learning-management"//Nếu là Keycloak
  //HOME_PAGE:APPLICATION_PATH+"landing3"//Link trang landing khi bắt đầu
});