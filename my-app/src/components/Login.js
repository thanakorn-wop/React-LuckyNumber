// import axios from "axios";
import React, { useContext, useState } from "react";
import "../CSS/Login.css"
import * as urlConstant from "../components/Constant/UrlConstant"
import { useNavigate } from "react-router-dom";
// import {useUser} from "../components/Authen/AuthenProvider"
//  import {Authen} from "../components/Authen/Authen"
import home from "../Icons/home.png"
import  axios  from "./Axios/useAxios";
import {AuthContext} from "../components/Authen/AuthenProvider"
function Login()
{
    
    // const axiosProviver = axiosProvider();
    // console.log("qq is = ",qq)
    const {auth, setAuth} = useContext(AuthContext);
    const [user,setUser] = useState({})
    const [test,settest] = useState(false);
    // const {handleLogin} = useUser();
    // const data = [1,2,3,4]
   
    // setAuth("20")
    // console.log(data);
    // console.log("check setIsLoggedIn  ",setIsLoggedIn )
    sessionStorage.setItem("token","");
    let navigate = useNavigate();
  
    // axios.interceptors.response.use(undefined,(error) =>{
    //     const {status,data,config} = error.response;
    //     console.log("check error =",data)
    //     if(status === 404)
    //     {
    //       window.location.assign("pagenotfound")
    //     }
    //     if(status === 400)
    //     {
    //         alert("BAD Request 400")
    //     }
    //     if(status ===500)
    //     {
    //       console.log("error server");
    //       alert("ERROR 500")
    //     }
    //     if(status === 401)
    //     {
    //          window.location.assign("/login")
    //     }
    //     if(status ===403)
    //     {
    //       window.location.assign("/login")
    //     }
    //   })

  

    function handleChange(e)
    {
        setUser({...user,[e.target.name]:e.target.value})
         console.log(user);
    }
   async  function submitLogin()
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

                const reponse = await axios.post(urlConstant.LOGIN_USER,user)
                console.log("check login  = ",reponse)
             
                    if(reponse.data !== null && reponse.data !== undefined)
                        {
                         
                             // window.location.assign("/dashbord")
                             console.log(reponse.data)
                             if(reponse.data.status == "L")
                             {
                                alert("บัญชีถูกล็อคการใช้งาน")
                             }
                             else if (reponse.data.status === "I")
                             {
                                alert("ไม่สามารถเข้าใช้งานบัญชีนี้ได้ในขณะนี้");
                             }
                             else{
                                const accessToken = reponse.data.accessToken;
                                console.log("accessToken = ",auth)
                                let a = {a:1,b:2}
                                let b = {c:3,d:4}
                                // console.log("check a ",)
                                setAuth({...auth,accessToken:accessToken,role:reponse.data.role,refreshToken:reponse.data.refreshToken})
                   
                                navigate("/dashboard") 
                             }
                        //    else{
                        //         if(reponse.data.iduser != null  )
                        //         {
                        //         alert(" ไอดี หรือ รหัสผ่านไม่ถูกต้อง")
                        //         }
                        //         else{
                        //             alert("กรุณาสมัครสมาชิก")
                        //         }
                        //    }
                        }
                
            
            
               
            }
            catch(error)
            {
                console.error(error);
                console.log("check error = ",error)
            }
        
        }
    }
    return(
        <div className="Mainpage">
           
            <div className="login" style={{"border":"solid 10px"}}>
                <div className="logoLogin" style={{"display":"flex","alignItems":"center","marginTop":"20px"}}> 
                    <div className="imghome" style={{"width":"40%","textAlign":"right"}}>
                        <img src={home} style={{"width":"15%"}}/>
                    </div>
                    <div className="labelLogin" style={{"width":"50%","marginLeft":"5px"}}>
                        <label style={{"color":"white","fontSize":"24px"}}>BAN HUAI</label>
                    </div>
                </div>
                <div className="formLogin">
                    <div className="textbox" >
                        <label className="labLogin" style={{"color":"white","fontSize":"20px"}}>ID</label>
                        <input className="textLogin" style={{"paddingBottom":"10px","width":"100%","borderTop":"0px","borderRight":"0px","borderLeft":"0px","borderBottom":"2px solid white","outline":"none","padding":"10px"}} name = "username" onChange = {(e)=>handleChange(e)} type="text"/>

                    </div>
                    <div className="textbox">
                        <label className="labLogin" style={{"color":"white","fontSize":"20px"}}>Password</label>  
                        <input  className="textLogin" style={{"paddingBottom":"10px","width":"100%","borderTop":"0px","borderRight":"0px","borderLeft":"0px","borderBottom":"2px solid white","outline":"none","padding":"10px"}} name = "password" onChange = {(e)=>handleChange(e)} type="password"/>
                    </div>
                    <div className="btnlogin" style={{"textAlign":"center","marginTop":"30px"}} > <button  style={{"width":"92%","fontSize":"20px"}}type="button" className="btn btn-primary" onClick={()=>submitLogin()}>Login</button></div>
                </div>
            </div>
        </div>
    )
}
export default Login;