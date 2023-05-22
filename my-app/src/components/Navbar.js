import React, { useContext } from "react";
import "../CSS/Navbar.css"
import { Link, NavLink } from 'react-router-dom';
import * as urlConstant from "../components/Constant/UrlConstant"
import {AuthContext} from "../App"

function Navbar()
{   const auth = useContext(AuthContext);
    console.log("check nav auth1 = ",auth)
    function logout(){
        let session =  sessionStorage.getItem("token");
        sessionStorage.removeItem("token");

    }
    return(

        <div className="Navbar">
            <div className="Box-Menu">
                <div className="Menu">
                    <div className="Logo"><label style={{"fontSize":"24px","marginLeft":"15px"}}>Nueng</label></div>
                    <div className="Link">
                        <ul className=" setmenu">
                            <li><NavLink to="/dashboard" > หน้าหลัก </NavLink></li>
                        {
                            auth === 'member'?     <li><NavLink to="/calculate" > คิดหวย </NavLink></li>  : null
                        }
                        {
                            auth !== 'member'?   <li><NavLink to="/report" > รายงาน </NavLink></li> : null
                        }
                            <li><NavLink to="/summary" > ยอดสรุป </NavLink></li>
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