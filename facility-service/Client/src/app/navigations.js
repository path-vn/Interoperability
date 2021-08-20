import ConstantList from "./appConfig";
export const navigations = [
  {
    name: "Dashboard.directory",
    isVisible: true,
    icon: "dashboard",
    children: [
      {
        name: "administrativeUnit.title",
        icon: "keyboard_arrow_right",
        path: ConstantList.ROOT_PATH + "administrative-unit",
        isVisible: true
      }, 
      {
        name: "healthOrganization.title",
        icon: "keyboard_arrow_right",
        path: ConstantList.ROOT_PATH + "health-organization",
        isVisible: true
      }, 
    ]
  },
  {
    name: "Dashboard.systemAdressCode",
    icon: "feed",
    path: ConstantList.ROOT_PATH + "system-adress-code",
    isVisible: true
  },
  {
    name: "Dashboard.systemHealthOrganizationCode",
    icon: "feed",
    path: ConstantList.ROOT_PATH + "system-health-org-code",
    isVisible: true
  }
];


 export const navigationsUser = [
 ];
