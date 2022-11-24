import React from "react";
import "../../CSS/PageNotFound.css"
import x from "../../Icons/delete.png"
function PangeNotFound()
{
    return(
       <div className="container">
        {/* <div className="pageerror" style={{"margin":"0 auto"}}>
          <h2>sdsd</h2> 
        </div> */}
        <div className="errpage" style={{"textAlign":"center"}}>
            <table className="table table-borderless" style={{"width":"100%"}}>
                <thead>
                    <tr className="trerrorpage" >
                        <td><img src={x} className="x" style = {{"width":"45px","height":"45px"}}/></td>
                    </tr>
                    <tr className="trerrorpage">
                        <td className="tderrorpage"><p style={{"backgroundColor":"white","color":"black"}}>ไม่สามารถค้นหา Page หรือ URl ที่คุณต้องการเจอ</p></td>
                    </tr>
                    <tr>
                        <td ><button type="button" style={{"color":"white","width":"100px","backgroundColor":"black"}}>กลับ</button></td>
                    </tr>
                </thead>
            </table>
        </div>
        
       </div>
    );
}
export default PangeNotFound;