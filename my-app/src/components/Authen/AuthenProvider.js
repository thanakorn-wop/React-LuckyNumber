import React, { createContext, useEffect, useState } from "react";

export const AuthContext = createContext({});

export const AuthProvider = ({ children }) => {
    const [auth, setAuth] = useState(()=>{
        const storedAuth = localStorage.getItem("authen")
        return storedAuth ? JSON.parse(storedAuth) : null;
    });
    console.log("default = ",auth)
    const login = (authen) => {
        console.log("login authen = ", authen);
        localStorage.setItem("authen", JSON.stringify(authen));
        setAuth(authen); // Update the state
    }
    useEffect(() => {
        console.log("Effect")
        const storedAuth = localStorage.getItem('authen');
        if (storedAuth) {
            setAuth(JSON.parse(storedAuth));
        }
    }, []); // Run this effect only once when the component mounts


    return (
        <AuthContext.Provider value={{ login,auth, setAuth }}>
            {children}
        </AuthContext.Provider>
    )
}

export default AuthContext;