import React from 'react';
import Dashbord from "./Dashbord";
import { Route, Link, BrowserRouter,Routes,Navigate } from 'react-router-dom'  
import { useContext } from 'react';
import AuthContext from './Authen/AuthenProvider';
function PrivateRoute({children}) {
  
   const isAuthenticated = useContext(AuthContext);
 const token =localStorage.getItem("accessToken");
  //  console.log("check token = ",token)
  //  console.log("check isAuthenticated1 = ",isAuthenticated)
  return (
    token? <>{children}</> : <Navigate to="/login" />
  
 
  );
}

export default PrivateRoute;