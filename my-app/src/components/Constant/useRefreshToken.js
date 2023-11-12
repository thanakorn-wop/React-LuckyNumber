import * as urlConstants from "./UrlConstant"
import React,{useContext} from "react";
import  AuthContext from "../Authen/AuthenProvider"

const useRefreshToken = ()=>{
    const { auth,setAuth } = useContext(AuthContext);
    // const [refreshToken, setRefreshToken] = useLocalStorage('refreshToken', '');
    // const [username] = useState('a');
    // const [password] = useState('a');
    return "test"
}
export default useRefreshToken