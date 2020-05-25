import axios from "axios";

export const baseUrl = "http://localhost:8086";
export const comicsUrl = "http://localhost:8083";
export const catalogueUrl = "http://localhost:8082";

export const user = "/user";
export const catalogue = "/katalog";
export const comicbook = "/strip";

const authenticatedApi = axios.create({
    baseURL: "http://localhost:8086"
});

authenticatedApi.interceptors.request.use(function(config){
    config.headers.Authorization = `Bearer ${localStorage.getItem("jwt")}`;
    return config;
});

export {authenticatedApi};