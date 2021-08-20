import localStorageService from "./localStorageService";
const roleSystem = {
  ROLE_ADMIN: "ROLE_ADMIN",
  ROLE_SUPER_ADMIN: "ROLE_SUPER_ADMIN",
  ROLE_USER: "ROLE_USER",
};

class UserRoles {
  isAdmin = (user) => {
    if (user == null) user = localStorageService.getItem("auth_user");
    if (user != null) {
      let roleAdminIndex = user.roles?.findIndex((role) => {
        return role.name == roleSystem.ROLE_ADMIN
      });
      return roleAdminIndex > -1;
    }
    return false;
  }

  isUser = (user) => {
    if (user == null) user = localStorageService.getItem("auth_user");
    if (user != null) {
      let roleUserIndex = user.roles?.findIndex((role) => {
        return role.name == roleSystem.ROLE_USER
      });
      return roleUserIndex > -1;
    }
    return false;
  }

}
export default new UserRoles();