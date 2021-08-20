import ConstantList from "./appConfig";
export const navigations = [

  {
    name: "Dashboard.dashboard",
    icon: "dashboard",
    path: ConstantList.ROOT_PATH + "dashboard/analytics",
    isVisible:true,
    
  },

  // {
  //   name: "Upload File",
  //   path: ConstantList.ROOT_PATH+"dashboard/upload_file",
  //   icon: "dashboard",
  //   isVisible:true,
  // },
  {
    name: "Hiv Patient",
    path: ConstantList.ROOT_PATH+"dashboard/patient",
    icon: "dashboard",
    isVisible:true,
  },

  // {
  //   name: "Data Source",
  //   path: ConstantList.ROOT_PATH+"list/DataSource",
  //   icon: "dashboard",
  //   isVisible:true,
  // },


  // {
  //   name: "Dashboard.manage",
  //   isVisible:true,
  //   icon: "engineering",
  //   children: [
  //     {
  //       name: "manage.user",
  //       isVisible:true,
  //       path: ConstantList.ROOT_PATH + "user_manager/user",
  //       icon: "keyboard_arrow_right"
  //     }
      
  //   ]
  // }
];
