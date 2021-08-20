import axios from "axios";
import UserService from "./UserService";

const HttpMethods = {
  GET: 'GET',
  POST: 'POST',
  DELETE: 'DELETE',
};

const _axios = axios.create();

const configure = () => {
  _axios.interceptors.request.use((config) => {
    const cb = () => {
      //config.header("Access-Control-Allow-Origin", "http://localhost:3000");
      //config.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
      //config.headers['Access-Control-Allow-Origin']= "http://localhost:3000";
      //config.headers['Access-Control-Allow-Methods'] ="Origin, X-Requested-With, Content-Type, Accept";      
      config.headers.Authorization = `Bearer ${UserService.getToken()}`;
      return Promise.resolve(config);
    };
    return UserService.updateToken(cb);
  });
};

const getAxiosClient = () => _axios;

export default {
  HttpMethods,
  configure,
  getAxiosClient,
}
