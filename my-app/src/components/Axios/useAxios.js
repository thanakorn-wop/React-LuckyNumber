import React, { useContext, useEffect } from "react";
import {AuthContext} from "../Authen/AuthenProvider"
import axios from "axios";
import useRefreshToken from "../Constant/useRefreshToken"


export default axios.create({
    baseURL: "http://localhost:8090"
});

// export const useAxios = ()=>  axios.create({
//     baseURL: "http://localhost:8090",
//     headers: {
//         'Content-Type': 'application/json',
//       },
// });
export  const useAxiosProvider = ()=>{
    const refreshToken = useRefreshToken();
  
    const useAxios = axios.create({
        baseURL: 'http://localhost:8090',
        headers: {
          'Content-Type': 'application/json',
        },
      });
        const {auth} = useContext(AuthContext)
        // console.log("auth check = ",auth)
        useEffect(() => {
            // Create an Axios instance with your configurations
         
        
            // Add an interceptor to set the 'Authorization' header
            const requestIntercept = useAxios.interceptors.request.use(
              (config) => {
                if (!config.headers['Authorization']) {
                  config.headers['Authorization'] = `Bearer ${auth.accessToken}`;
                }
                return config;
              },
              (error) => Promise.reject(error)
            );

          //   const responseIntercept = useAxios.interceptors.response.use(
          //     response => response,
          //     async (error) => {
          //         const prevRequest = error?.config;
          //         if(error?.response?.status === 401 && !prevRequest?.sent){ // Unauthorized
          //             prevRequest.sent = true;
          //             const newAccessToken = await refreshToken();
          //             prevRequest.headers['Authorization'] = `Bearer ${newAccessToken}`;
          //             return useAxios(prevRequest);
          //         }
          //         // else if(error?.response?.status === 403){ // Forbidden
          //         //     console.log('Error 403');
          //         //     navigate('/login', { state: { from: location }, replace: true });
          //         // }else if(error?.response?.status === 404){ // Not Found
          //         //     navigate('/noPageFound', { state: { from: location }, replace: true });
          //         // }else if(error?.response?.status === 400){ // Bad Request
          //         //     console.log('Error 400');
          //         // }else if(error?.response?.status === 405){ // Method Not Allowed method
          //         //     console.log('Error 405');
          //         // }else if(error?.response?.status === 500){ // Internal server error
          //         //     console.log('Error 500');
          //         // }else{
          //         //     console.log('Error Other');
          //         // }
          //         return Promise.reject(error);
          //     }
          // );
             //   axios.interceptors.response.use(undefined,(error) =>{
    //     const {status,data,config} = error.response;
    //     if(status === 404)
    //     {
    //       window.location.assign("/pagenotfound")
    //     }
    //     if(status === 400)
    //     {
    //         alert("BAD Request 400")
    //     }
    //     if(status ===500)
    //     {
    //       console.log("error server");
           
    //        window.location.assign("/internalserver")
    //     }
    //     if(status === 401)
    //     {
    //          window.location.assign("/login")
    //     }
    //     if(status ===403)
    //     {
    //       window.location.assign("/login")
    //     }
        
            // Clean up the interceptor when the component unmounts
            // return () => {
            //     useAxios.interceptors.request.eject(requestIntercept);
            // };
          }, [auth]);
        
          return useAxios; // Return the Axios instance
}