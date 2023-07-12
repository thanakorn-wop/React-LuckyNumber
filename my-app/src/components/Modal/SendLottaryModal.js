import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "../../CSS/ModalCss/SendLottaryModal.css"
function SendLottaryModal(props)
{
    const [DateMonth,setDateMonth] = useState(new Date());
    let date = {luckytime:""}
    function SaveData(status)
    {
        if(status ==="Yes")
        {
            date["luckytime"] = DateMonth.getFullYear()+"-"+(1+Number(DateMonth.getMonth()))+"-"+DateMonth.getDate();
            props.onClose(status,date)
        }
        else{
            props.onClose(status)
        }
    }
    return(
        <div className="sendlottarymodal" >
            <div className="modal-header" style={{"borderBottom":"solid gray"}} >
                <div className="header-title" style={{"padding":"15px","color":"white"}}><h4>ทำรายการส่งหวย</h4></div>
            </div>
            <div className="boxbody">
                <div  className ="dateLottary" style={{"marginTop":"15px","marginLeft":"15px"}}>
                   <label className="textsendlottary" style={{"color":"white"}}>งวดที่ต้องการส่ง</label>
                    <DatePicker className="form-control "  selected={DateMonth}   dateFormat= "dd-MM-yyyy" onChange={(date) => setDateMonth(date)} /> 
                 
             
                </div>
            </div>
            <div className="modal-footer" style={{"marginTop":"15px","marginBottom":"15px","borderTop":"solid gray"}}>
                <div className="footer">
                    <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" name = "No" onClick={(e) => SaveData(e.target.name)}>Close</button>
                    <button type="button" className="btn btn-primary" style={{"marginLeft":"10px"}} data-bs-dismiss="modal" name = "Yes" onClick={(e) => SaveData(e.target.name)}>Yes</button> 
                </div>
            
                 {/* <button type="button" className="btn btn-primary" name = "save" onClick={(e)=>saveData(e)}>Save changes</button> */}
            </div>
        </div>
    )
}
export default  SendLottaryModal;