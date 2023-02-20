import React, { useState } from "react";
import "../../CSS/ModalCss/PaymentStatusModal.css"
function PaymentStatusModal (props)
{
    const [Data, setData] = useState({
      
        status:"" 
    });

    const handleChange = (e) => {
        const value = e.target.value;
        setData({...Data,[ e.target.name]: value});
    }

    const saveData = (e)=>{
        // console.log(e.target.name)
        // props.Data(numberData)
        // console.log("check data",numberData)
        // props.status(e.target.name)
        // props.onClose(false)
        props.HandlePayment(e.target.name,Data)
       

        // console.log("check data = ",numberData)
    }
   
    return(
        <div className="paymentmodal" >
                <div className="modal-header" style={{"textAlign":"center"}} ><h4>Modal title</h4></div>
                <div className="boxbody">
                    <div style={{"marginLeft":"10px","marginRight":"20px"}}>
                    <div className="text">  <label>สถานะการจ่าย</label></div>
                    <div> 
                        <select className="form-select form-select-sm "  name = "status"   style={{"width":"15%"}} onChange={(e)=>handleChange(e)}>
                            <option value="empty
                            ">เลือก</option>
                            <option value="Yes">จ่ายแล้ว</option>
                            {/* <option value = "No">ยังไม่จ่าย</option> */}
                        </select>
                    </div>
                 
                    </div>
                </div>
                <div className="modal-footer" style={{"marginTop":"20px"}}>
                    <button type="button" className="btn btn-secondary" data-bs-dismiss="modal"  name = "notsave" onClick={(e)=>saveData(e)}>Close</button>
                    <button type="button" className="btn btn-primary" name = "save" onClick={(e)=>saveData(e)}>Save changes</button>
                </div>
        </div>
    );

}
export default PaymentStatusModal;