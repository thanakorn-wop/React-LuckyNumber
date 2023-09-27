import * as urlConstants from "./UrlConstant"
import React,{useContext} from "react";
import  AuthContext from "../Authen/AuthenProvider"
const useRefreshToken = ()=>{
    const {auth,setAuth} = useContext(AuthContext);
    console.log("refresh auth = ",auth)
    return "test"
}
export default useRefreshToken