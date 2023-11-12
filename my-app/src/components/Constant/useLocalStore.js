import React from "react";
import { useContext } from "react";
import AuthContext from "../Authen/AuthenProvider";
import { Outlet } from "react-router-dom";
import { useEffect } from "react";


function UseLocalStorage() {
  const { auth, setAuth } = useContext(AuthContext);
  console.log("useLocal = ",auth )
  // getToken("accessToken", auth.accessToken);
  useEffect(() => {
    // getToken("accessToken", auth.accessToken);
    localStorage.setItem("accessToken", auth.accessToken);
    // setAuth("qqqtest");
  }, [auth.accessToken]);


  const getToken = (key, value) => {
    console.log("check ",localStorage.getItem(key))
    const isToken = localStorage.getItem(key) == "undefined" ? true : false;
    if (isToken) {
      localStorage.setItem(key, value);
      
    }
    else{
      localStorage.setItem(key, value);
    }
    //    console.log("isToken ",isToken)
  };
  return <Outlet />;
}
export default UseLocalStorage;
