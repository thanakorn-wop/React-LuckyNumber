import React from 'react';
import Dashbord from "./Dashbord";
import { Route, Link, BrowserRouter,Routes,Navigate } from 'react-router-dom'  
import { useContext } from 'react';
import AuthContext from './Authen/AuthenProvider';
function PrivateRoute({children}) {
 
   const isAuthenticated = useContext(AuthContext);
   console.log("check isAuthenticated = ",isAuthenticated)
  return (
    isAuthenticated.auth.accessToken ? <>{children}</> : <Navigate to="/login" />
  
 
  );
}

export default PrivateRoute;