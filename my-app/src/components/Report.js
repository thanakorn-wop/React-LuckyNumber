import React, { useState } from "react";
import "../CSS/Report.css"
import EditinfoModal from "./Modal/EditinfoModal";
import "../CSS/ModalCss/EditinfoModal.css"

function Report ()
{
    const [isOpen ,setIsOpen] = useState(false);
    const [cost,setCost] = useState(0);
    const [month,setMonth] = useState([1,2,3,4,5,6,7,8,9,10,11,12])
    
    const monthjsx = 
    (
        month.map(data =>{
            return <option>{data}</option>
         })
    )
    return(
        <div className="pathreport">
            <div className="boxreport">
                <div className="title">
                    <h3>Report</h3>
                </div>
                <div>  
                    <button type="button" className="btn btn-warning" style={{"marginLeft":"135px","marginBottom":"10px"}} onClick={()=>setIsOpen(true)}>แก้ไขข้อมูล</button>
                    <select>
                        <option>--เลือก--</option>
                        {monthjsx}        
                    </select>
                </div>
                
                <table className="table">
                    <thead>
                        <tr style={{"border":"solid",}}>
                            <td>จำนวนต้นทุน : </td>
                            <td>{cost}</td>
                            <td>ยอดขาย : </td>
                            <td>3000000 บาท </td>
                            {/* <td>จำนวนคนถูก : </td>
                            <td>30 คน</td> */}
                        </tr>
                        <tr style={{"border":"solid",}}>
                              <td>จำนวนคนถูก : </td>
                            <td>30 คน</td>
                              <td>จำนวนคนไม่ถูก : </td>
                            <td>50 คน</td>
                        </tr>
                        <tr style={{"border":"solid"}}>
                            <td>จำนวนเงินคนที่ถูก : </td>
                            <td>1,000,000 บาท</td>
                            <td>ยอดคงเหลือ : </td>
                            <td>1,000,000 บาท</td>
                        </tr>
                        <tr style={{"border":"solid"}}>
                            <td>จ่ายไปแล้วเป็นจำนวน : </td>
                            <td>5000 บาท</td>
                            <td>ยังค้างเหลืออยู่ : </td>
                            <td>3000 บาท</td>
                        </tr>
                    </thead>
                </table>
                {
                    isOpen && <EditinfoModal onClose = {(e) => setIsOpen(false)} Data = {(e)=>setCost(e)}/>
                }
            </div>
        </div>

    );

}
export default Report;