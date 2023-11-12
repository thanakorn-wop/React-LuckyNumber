import React, { useContext, useEffect } from "react";
import {AuthContext} from "../Authen/AuthenProvider"
import axios from "axios";
import useRefreshToken from "../Constant/useRefreshToken"


// export default axios.create({
//     baseURL: "http://localhost:8090"
// });


  const useAxiosProvider = ()=>{
    const refreshToken = useRefreshToken();
    const {auth} = useContext(AuthContext)
    const token = auth.accessToken;
    const useAxios = axios.create({
        baseURL: 'http://localhost:8090',
        headers: {
          'Content-Type': 'application/json',
        },
      });
      console.log("token = ",token)
        // console.log("auth check = ",auth)
        useEffect(() => {
          useAxios.interceptors.request.use(function (config) {
            // Do something before request is sent
            if (auth && auth.accessToken) {
              // Add Bearer token to Authorization header
              config.headers['Authorization'] = `Bearer ${auth.accessToken}`;
            }
            return config;
          }, function (error) {
            // Do something with request error
            return Promise.reject(error);
          });
        }, [auth]);
        
          return useAxios; // Return the Axios instance
}
export default useAxiosProvider;