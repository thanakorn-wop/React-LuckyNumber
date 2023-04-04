import React from "react";
import deleteimg from "../../Icons/delete.png"
function InternalServer()
{
    return(
        <div className="containerInternal"  style={{"position":"absolute","top":"35%","width":"30%","left":"35%"}}>
        {/* <div className="pageerror" style={{"margin":"0 auto"}}>
          <h2>sdsd</h2> 
        </div> */}
        <div className="errpage" style={{"textAlign":"center"}}>
            <table className="table table-borderless" style={{"width":"100%"}}>
                <thead>
                    <tr className="trerrorpage" >
                        <td><img src={deleteimg} className="deleteimg" style = {{"width":"45px","height":"45px"}}/></td>
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
        
       </div>
    )
}
export default InternalServer;