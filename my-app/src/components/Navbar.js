import React from "react";
import "../CSS/Navbar.css"
import { Link } from 'react-router-dom';
import * as urlConstant from "../components/Constant/UrlConstant"
import axios from "axios";
function Navbar()
{
    function logout(){
        let session =  sessionStorage.getItem("token");
       if(session === undefined || session === null)
       {
        // window.location.assign("/login")
        console.log("no session ")
        return;
       }
       else{
        try{
            axios.post(urlConstant.LOGOUT_USER,{token:session},{
                headers: { 'Content-Type': 'application/json' }
            }).then(res =>{
                console.log(res)
                if(res.status.ok == 200)
                {
                    sessionStorage.removeItem("token");
                    window.location.assign("/login")
                }
            })
        }catch(e){
            console.log(e)
        }
       
      
       }

//  sessionStorage.setItem("pageView", pageView);
    }
    return(

        <div className="Navbar">
            <div className="Box-Menu">
                <div className="Menu">
                    <div className="Logo"><label style={{"fontSize":"24px","marginLeft":"15px"}}>Nueng</label></div>
                    <div className="Link">
                        <ul className=" setmenu">
                            <li><a href="dashboard" > หน้าหลัก </a></li>
                            <li><a href="calculate" > คิดหวย </a></li>
                            <li><a href="report" > ยอดสรุป </a></li>
                            <li><a href="contactus" > ติดต่อเรา </a></li>
                            <li><a href="login" onClick={()=> logout()} > ออกจากระบบ </a></li>
                           
                        </ul>
                    </div>
                   
                </div>
            </div>
            
            
        </div>
    );
}
export default Navbar;