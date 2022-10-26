import axios from "axios";
import React, { useState } from "react";
import "../CSS/Login.css"
import * as urlConstant from "../components/Constant/UrlConstant"
function Login()
{
    const [user,setUser] = useState({id:""})
    const [test,settest] = useState(false);
    console.log("befor",test)
    function handleChange(e)
    {
        setUser({...user,[e.target.name]:e.target.value})
        // console.log(user);
    }
    function submitLogin()
    {
      
    
       
        
        if(user.id === undefined || user.id ==="")
        {
            alert("กรุณากรอก ID")
        }
        else if(user.password === undefined || user.password ==="")
        {
            alert("กรุณากรอก PASSWORD")
        }
        else{
            const response = axios.post(urlConstant.LOGIN_USER,user,{
                headers: { 'Content-Type': 'application/json' }
            })
        }
    }
    return(
        <div className="Mainpage">
            <div className="login" style={{"border":"solid 10px "}}>
                <div><h2 style={{"color":"white","textAlign":"center","marginTop":"20px"}}>Login</h2></div>
                <div className="formLogin">
                    <div className="textbox">
                        <label style={{"color":"white"}}>ID</label>
                        <input className="form-control" name = "id" onChange = {(e)=>handleChange(e)}type="text"/>
                    </div>
                    <div className="textbox">
                        <label style={{"color":"white"}}>Password</label>
                        <input  className="form-control" name = "password" onChange = {(e)=>handleChange(e)} type="text"/>
                    </div>
                    <div style={{"marginLeft":"20px"}}> <button type="button" className="btn btn-light" onClick={()=>submitLogin()}>Login</button></div>
                </div>
            </div>


        </div>
    )
}
export default Login;