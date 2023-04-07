import React from "react";
import "../CSS/Navbar.css"
import { Link, NavLink } from 'react-router-dom';
import * as urlConstant from "../components/Constant/UrlConstant"
import axios from "axios";
function Navbar()
{
    function logout(){
        let session =  sessionStorage.getItem("token");
        sessionStorage.removeItem("token");
    //    if(session === undefined || session === null)
    //    {
    //     // window.location.assign("/login")
    //     console.log("no session ")
    //     return;
    //    }
    //    else{
    //     try{
    //         axios.post(urlConstant.LOGOUT_USER,{token:session},{
    //             headers: { 'Content-Type': 'application/json' }
    //         }).then(res =>{
    //             console.log(res)
    //             if(res.status.ok == 200)
    //             {
    //                 sessionStorage.removeItem("token");
    //                 window.location.assign("/login")
    //             }
    //         })
    //     }catch(e){
    //         console.log(e)
    //     }
       
      
    //    }

//  sessionStorage.setItem("pageView", pageView);
    }
    return(

        <div className="Navbar">
            <div className="Box-Menu">
                <div className="Menu">
                    <div className="Logo"><label style={{"fontSize":"24px","marginLeft":"15px"}}>Nueng</label></div>
                    <div className="Link">
                        <ul className=" setmenu">
                            <li><NavLink to="/dashboard" > หน้าหลัก </NavLink></li>
                            <li><NavLink to="/calculate" > คิดหวย </NavLink></li>
                            <li><NavLink to="/report" > ยอดสรุป </NavLink></li>
                            <li><NavLink to="/contactus" > ติดต่อเรา </NavLink></li>
                            <li><NavLink to="/login" onClick={()=> logout()} > ออกจากระบบ </NavLink></li>
                           
                        </ul>
                    </div>
                   
                </div>
            </div>
            
            
        </div>
    );
}
export default Navbar;