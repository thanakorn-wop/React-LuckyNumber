import React from "react";
import { useContext } from "react";
import AuthContext from "../Authen/AuthenProvider";
import { Outlet } from "react-router-dom";
import { useEffect } from "react";
const getToken = (key, value) => {
  const isToken = localStorage.getItem(key) == "undefined" ? true : false;
  if (isToken) {
    localStorage.setItem(key, value);
    
  }
  //    console.log("isToken ",isToken)
};

function UseLocalStorage() {
  const { auth, setAuth } = useContext(AuthContext);
  // getToken("accessToken", auth.accessToken);
  useEffect(() => {
    getToken("accessToken", auth.accessToken);
    
    // setAuth("qqqtest");
  }, [auth.accessToken, setAuth]);

  return <Outlet />;
}
export default UseLocalStorage;
