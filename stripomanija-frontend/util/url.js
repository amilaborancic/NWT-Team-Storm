import axios from "axios";

export const baseUrl = "http://localhost:8086";

export const user = "/user";

const authenticatedApi = axios.create({
    baseURL: "http://localhost:8086"
});

authenticatedApi.interceptors.request.use(function(config){
    config.headers.Authorization = `Bearer ${localStorage.getItem("jwt")}`;
    return config;
});


export {authenticatedApi};