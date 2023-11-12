import React from 'react';
import Dashbord from "./Dashbord";
import { Route, Link, BrowserRouter,Routes,Navigate } from 'react-router-dom'  
import { useContext } from 'react';
import AuthContext from './Authen/AuthenProvider';
function PrivateRoute({children}) {
  
  const {auth,setAuth} = useContext(AuthContext);
  const token = auth.accessToken
  //  console.log("check token = ",auth)
  //  console.log("check isAuthenticated1 = ",isAuthenticated)
  return (
    token? <>{children}</> :<Navigate to="/login" />
    // token? <>{children}</> : <>{children}</>
  
 
  );
}

export default PrivateRoute;