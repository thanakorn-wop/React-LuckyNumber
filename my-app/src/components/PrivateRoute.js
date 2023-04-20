import React from 'react';
import Dashbord from "./Dashbord";
import { Route, Link, BrowserRouter,Routes,Navigate } from 'react-router-dom'  

function PrivateRoute({children}) {
   const isAuthenticated = sessionStorage.getItem('token');
   console.log("check isAuthenticated = ",isAuthenticated)
  return (
    isAuthenticated ? <>{children}</> : <Navigate to="/login" />
  
 
  );
}

export default PrivateRoute;