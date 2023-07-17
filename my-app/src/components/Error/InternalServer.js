import React from "react";
import deleteimg from "../../Icons/delete.png"
import "../../CSS/ErrorCSS/ErrorInternalServer.css"
function InternalServer()
{
    return(
        <div className="errpageInternal" style={{"textAlign":"center"}}>
            <table className="table table-borderless" style={{"width":"100%"}}>
                <thead>
                    <tr className="trerrorpage" >
                        <td><img src={deleteimg} alt="error" className="deleteimg" style = {{"width":"45px","height":"45px"}}/></td>
                    </tr>
                    <tr className="trerrorpage">
                        <td className="tderrorpage"><p style={{"backgroundColor":"white","color":"black"}}>ระบบมีปัญหากรุณาติดต่อผู้ดูแล</p></td>
                    </tr>
                    <tr className="trerrorpage">
                        <td ><button type="button" style={{"color":"white","width":"100px","backgroundColor":"black"}}>กลับ</button></td>
                    </tr>
                </thead>
            </table>
        </div>
    )
}
export default InternalServer;