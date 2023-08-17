import React, { useState } from "react";
import "../../CSS/ModalCss/PaymentStatusModal.css"
function PaymentStatusModal (props)
{
    
    const [payment,setPayment]= useState(props.payment)
    console.log("payment = ",payment)
    const [Data, setData] = useState({
      
        status:''
    });


    const handleChange = (e) => {
        const value = e.target.value;
        setData({...Data,[ e.target.name]: value});
        setPayment(value)
      
    }

    const saveData = (e)=>{
   
        props.HandlePayment(Data,e)
       

        // console.log("check data = ",numberData)
    }
   
    return(
        <div className="paymentmodal" >
                <div className="modal-header" style={{"textAlign":"center","borderBottom":"solid gray"}} > <div className="header-title" style={{"padding":"15px"}}> <h4>สถานะการจ่ายเงิน</h4></div></div>
                <div className="boxbody">
                    <div  className = "modal-body" style={{"marginLeft":"10px","marginRight":"20px","marginTop":"20px"}}>
                  
                    
                    <label>สถานะการจ่าย</label>
                    <select className="select-status" value = {payment}  name = "status"   style={{"width":"15%","padding":"5px","marginLeft":"5px"}} onChange={(e)=>handleChange(e)}>
                            
                            <option value="Yes">จ่ายแล้ว</option>
                            <option value="No">ยังไม่จ่าย</option>
                            {/* <option value = "No">ยังไม่จ่าย</option> */}
                        </select>
                    
                    
                 
                    </div>
                </div>
                <div className="modal-footer" style={{"marginTop":"20px","marginBottom":"10px","borderTop":"solid gray"}}>
                    <div className="footer">
                        <button type="button" className="btn btn-secondary" data-bs-dismiss="modal"  name = "No" onClick={(e)=>saveData(e.target.name)}>Close</button>
                        <button type="button" className="btn btn-primary"  style={{"marginLeft":"10px"}} name = "Yes" onClick={(e)=>saveData(e.target.name)}>Save changes</button>
                    </div>
                </div>
        </div>
    );

}
export default PaymentStatusModal;