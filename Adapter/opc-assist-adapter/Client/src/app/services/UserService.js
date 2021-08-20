import Keycloak from "keycloak-js";
import ConstantsList from '../appConfig';
import JwtAuthService from './jwtAuthService';
const _kc = new Keycloak(ConstantsList.ROOT_PATH+'keycloak.json');

/**
 * Initializes Keycloak instance and calls the provided callback function if successfully authenticated.
 *
 * @param onAuthenticatedCallback
 */
const initKeycloak = (onAuthenticatedCallback) => {
  _kc.init({
    onLoad: 'check-sso',
    promiseType: 'native',
    silentCheckSsoRedirectUri: window.location.origin +'/'+ConstantsList.ROOT_PATH+ '/silent-check-sso.html',
    pkceMethod: 'S256',
  })
    .then((authenticated) => {
      if (authenticated) {
        JwtAuthService.setLoginToken(_kc.token);//Xem lại hàm này để làm chuẩn xác hơn
        onAuthenticatedCallback();
      } else {
        console.warn("not authenticated!");
        doLogin();
      }
    })
};

const doLogin = _kc.login;

const doLogout = _kc.logout;

const getToken = () => _kc.token;

const updateToken = (successCallback) => {
  return _kc.updateToken(5)
    .then(successCallback)
    .catch(doLogin)
};

const getUsername = () => _kc.tokenParsed.preferred_username;

export default {
  initKeycloak,
  doLogin,
  doLogout,
  getToken,
  updateToken,
  getUsername,
}
