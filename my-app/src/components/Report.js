import React, { useEffect, useState } from "react";
import "../CSS/Report.css"
import EditinfoModal from "./Modal/EditinfoModal";
import "../CSS/ModalCss/EditinfoModal.css"
import DatePicker from "react-datepicker";
import axios from "axios";
import * as urlConstant from "../components/Constant/UrlConstant"
function Report ()
{
    const [isOpen ,setIsOpen] = useState(false);
    const [cost,setCost] = useState(0);
    const [month,setMonth] = useState([1,2,3,4,5,6,7,8,9,10,11,12])
    const [DateMonth, setDateMonth] = useState(new Date());
    

    let session =  sessionStorage.getItem("token");
    if(session === null || session === undefined || session ==="")
    {
      window.location.assign("/login")
    }


    useEffect(()=>{
            async function GetReport()
            {
                const response = axios.get(urlConstant.GET_REPORT,{
                    headers: { 'Content-Type': 'application/json' }   
                }
                )
            }

    },[])
    const monthjsx = 
    (
        month.map(data =>{
            return <option key={data}>{data}</option>
         })
    )
    function searchData()
    {

    }


    return(
        <div className="pathreport">
            <div className="boxreport">
                <div className="title">
                    <h3>Report</h3>
                </div>
                <div style={{"display":"flex"}}>  
                    <div>
                        
                          <button type="button" className="btn btn-warning" style={{"marginLeft":"135px","marginBottom":"10px"}} onClick={()=>setIsOpen(true)}>แก้ไขข้อมูล</button>
                    </div>
                    <div style={{"marginLeft":"10px"}}>
                    <button type="button" className="btn btn-warning" onClick={()=>searchData()}>ค้นหา</button>
                    </div>
                    <div style={{"marginLeft":"10px","width":"10%"}}>
                        <DatePicker className="form-control "   selected={DateMonth}  onChange={(date) => setDateMonth(date)} />
                    </div>
                  
                </div>
                
                <table className="table" style={{"marginBottom":"15px"}}>
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