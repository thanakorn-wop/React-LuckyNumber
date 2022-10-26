import React from "react";
import "../CSS/Navbar.css"
import { Link } from 'react-router-dom';
function Navbar()
{
    return(

        <div className="Navbar">
            <div className="Box-Menu">
                <div className="Menu">
                    <div className="Logo"><label style={{"fontSize":"24px","marginLeft":"15px"}}>Nueng</label></div>
                    <div className="Link">
                        <ul>
                            <li><a href="/dashboard" > หน้าหลัก </a></li>
                            <li><a href="/calculate" > คิดหวย </a></li>
                            <li><a href="/report" > ยอดสรุป </a></li>
                            <li><a href="/contactus" > Contact us </a></li>
                            <li><a href="/logout" > ออกจากระบบ </a></li>
                           
                        </ul>
                    </div>
                   
                </div>
            </div>
            
            
        </div>
    );
}
export default Navbar;