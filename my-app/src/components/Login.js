import axios from "axios";
import React, { useContext, useState } from "react";
import "../CSS/Login.css"
import * as urlConstant from "../components/Constant/UrlConstant"
import { useNavigate } from "react-router-dom";
// import {useUser} from "../components/Authen/AuthenProvider"
//  import {Authen} from "../components/Authen/Authen"
function Login({ setauth   })
{
    
    const [user,setUser] = useState({})
    const [test,settest] = useState(false);
    // const {handleLogin} = useUser();
    // const data = [1,2,3,4]
   

    // console.log(data);
    // console.log("check setIsLoggedIn  ",setIsLoggedIn )
    sessionStorage.setItem("token","");
    let navigate = useNavigate();
  
    axios.interceptors.response.use(undefined,(error) =>{
        const {status,data,config} = error.response;
        if(status === 404)
        {
          window.location.assign("pagenotfound")
        }
        if(status === 400)
        {
            alert("BAD Request 400")
        }
        if(status ===500)
        {
          console.log("error server");
          alert("ERROR 500")
        }
        if(status === 401)
        {
             window.location.assign("/login")
        }
        if(status ===403)
        {
          window.location.assign("/login")
        }
      })

  

    function handleChange(e)
    {
        setUser({...user,[e.target.name]:e.target.value})
         console.log(user);
    }
    function submitLogin()
    {
        if(user.username === undefined || user.username ==="")
        {
            alert("กรุณากรอก ID")
        }
        else if(user.password === undefined || user.password ==="")
        {
            alert("กรุณากรอก PASSWORD")
        }
        else{
            try{
               axios.post(urlConstant.LOGIN_USER,user,{
                    headers: { 'Content-Type': 'application/json' }
                }).then(res =>{
                    console.log(res.data)
                  //  console.log(res.data.header)
                    if(res.data !== null && res.data !== undefined)
                    {
                     
                       if(res.data.header.statusCode ==="01")
                       {
                         // window.location.assign("/dashbord")
                         console.log(res.data[0])
                         if(res.data.status == "L")
                         {
                            alert("บัญชีถูกล็อคการใช้งาน")
                         }
                         else if (res.data.status === "I")
                         {
                            alert("ไม่สามารถเข้าใช้งานบัญชีนี้ได้ในขณะนี้");
                         }
                         else{
                            const accessToken = res.data.token;
    
                          //  setAuth = accessToken;
                        //  Outlet
                            // sessionStorage.setItem("token", res.data.token);
                             setauth(res.data)
                            //  handleLogin(res.data)
                            // console.log("check session  = ",sessionStorage.getItem("token"))
                            // setIsLoggedIn(true);
                            navigate("/dashboard") 
                         }
                       }
                       else{
                            if(res.data.iduser != null  )
                            {
                            alert(" ไอดี หรือ รหัสผ่านไม่ถูกต้อง")
                            }
                            else{
                                alert("กรุณาสมัครสมาชิก")
                            }
                          
                       }
                    }
                   
                }).catch((error)=>{
                    if(error.response)
                    {
                        console.log("status errorr = ",error.response.status)
                        alert("error")
                    }
                })
            }catch(error)
            {
                console.error(error);
                console.log("check error = ",error)
            }
        
        }
    }
    return(
        <div className="Mainpage">
           
            <div className="login" style={{"border":"solid 10px"}}>
                <div><h2 style={{"color":"white","textAlign":"center","marginTop":"20px"}}>Login</h2></div>
                <div className="formLogin">
                    <div className="textbox" >
                        <label style={{"color":"white","fontSize":"20px"}}>ID</label>
                        <div>
                        <input className="" style={{"paddingBottom":"10px","width":"100%","borderTop":"0px","borderRight":"0px","borderLeft":"0px","borderBottom":"2px solid white","outline":"none"}} name = "username" onChange = {(e)=>handleChange(e)}type="text"/>
                        </div>
                      
                    </div>
                    <div className="textbox">
                        <label style={{"color":"white","fontSize":"20px"}}>Password</label>
                        <div>
                        <input  className="" style={{"paddingBottom":"10px","width":"100%","borderTop":"0px","borderRight":"0px","borderLeft":"0px","borderBottom":"2px solid white","outline":"none"}} name = "password" onChange = {(e)=>handleChange(e)} type="password"/>
                        </div>
                      
                    </div>
                    <div className="btnlogin" style={{"textAlign":"center","marginTop":"30px"}} > <button  style={{"width":"92%","fontSize":"20px"}}type="button" className="btn btn-light" onClick={()=>submitLogin()}>Login</button></div>
                </div>
            </div>
        </div>
    )
}
export default Login;