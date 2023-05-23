import React, { useState } from "react";
import * as authen  from "../Authen/Authen"
import { useNavigate } from "react-router-dom";
import { useContext } from "react";


function AuthenProvider({children})
{
    const [user, setUser] = useState(null);
    let navigate = useNavigate();
    
    
    const handleLogin = (detailsUser) => {
        // Perform authentication logic here, e.g., make an API call
        // to verify the user's credentials and retrieve their role and permissions.
        const userAdmin = {iduser:"",role:"",permission:[]};
        console.log("AuthenProvider = ",detailsUser.role)
        // Simulating a successful login for demonstration purposes
        if(detailsUser.role === "admin")
        {
            userAdmin.iduser = detailsUser.iduser
            userAdmin.role = detailsUser.role
              console.log("check userAdmin2 = ",userAdmin)
        }
        else{
            userAdmin.iduser = detailsUser.iduser
            userAdmin.role = detailsUser.role
              console.log("check userAdmin2 = ",userAdmin)
        }
        console.log("check userAdmin = ",userAdmin)
        setUser(userAdmin);
        // navigate("/dashboard")  // Redirect to the dashboard or any other authorized page
      };

    return(
        <authen.Authen.Provider value = {{user,handleLogin}}>
            {children}
        </authen.Authen.Provider>

    );
}
export default AuthenProvider;