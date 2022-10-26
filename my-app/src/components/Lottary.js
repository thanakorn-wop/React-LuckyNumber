import React, { useState } from "react";
import "../CSS/Lottary.css"
import NumberModal from "./Modal/NumberModal"
import * as urlConstant from "../components/Constant/UrlConstant"
import InsertNumberModal from "./Modal/InsertNumberModal";
import PaymentStatusModal from "../components/Modal/PaymentStatusModal"
import InfoUserModal from "./Modal/infoUserModal"
import axios from 'axios';
function Lottary()
{
    const [popup,setpopup] =  useState(false);
    const [status,setStatus] = useState();
    const [luckyModal,setLuckyModal]  =useState(false);
    const [isOpenPaymentModal,setIsOpenPayMentModal] = useState(false);
    const [isOpenInfoUserModal,setInfoUserModal] = useState(false);
    
    const [ newData,setNewData] = useState({
        id: "",
        date: "", 
    });
    // console.log("check 1 = ",popup.show)
    function ValidityState()
    {
        setpopup(true);
       
    }
    function setData(e)
    {
        setNewData(e);
    }
    function submitData(e)
    {
        if(e === "save")
        {
            console.log("newdata = ",newData.date)
            if(newData.date === undefined || newData.date === "")
            {
                alert("กรุุณากรอก Date");
            }
            else if (newData.option === undefined || newData.option === "")
            {
                alert("กรุุณากรอก Option");
            }
            else if (newData.number === undefined || newData.number === "")
            {
                alert("กรุุณากรอก number");
            }
            else if (newData.price === undefined || newData.price === "")
            {
                alert("กรุุณากรอก price");
            }
            else{
                const response = axios.post(urlConstant.SAVE_NUMBER_BUYING,newData, {
                    headers: { 'Content-Type': 'application/json' }
                })
            }
         console.log("check e ",newData)
        }
    }
    // console.log(popup)
    return(
        <div className="mainpage">
            <div className="boxpage" >
                
           
                    <div className="title" style={{"textAlign":"center","marginTop":"20px"}}>
                       <h3>รายการหวย</h3>
                    </div>
                    <div className="info"> 
                        <table >
                            <thead>
                                <tr >
                                    <td > <button type="button" className="btn btn-light" style={{"marginLeft":"20px"}} onClick={()=>ValidityState()}>เพิ่มข้อมูล</button> </td>
                                    <td > <button type="button" className="btn btn-light" style={{"marginLeft":"20px"}} onClick={()=>setLuckyModal(true)}>เลขถูก</button> </td>
                                    <td ><label style={{"fontSize":"24px","marginLeft":"20px"}}>เงินต้น : 20000</label></td>
                                    <td ><label style={{"fontSize":"24px","marginLeft":"20px"}}>ยอดเงินคนถูก : 20000</label></td>
                                    <td ><label style={{"fontSize":"24px","marginLeft":"20px"}}>เลขหน้า 3 ตัว : 111 </label></td>
                                    <td ><label style={{"fontSize":"24px","marginLeft":"20px"}}>เลขท้าย 3 ตัว : 222</label></td>
                                    <td ><label style={{"fontSize":"24px","marginLeft":"20px"}}>เลขท้าย 2 ตัว : 33</label></td>
                                </tr>
                            </thead>
                        </table>
                    </div>

                    <div>
                        <table style={{"border":"solid 2px yellow","width":"80%","margin":"0 auto","marginTop":"50px"}}  className="table table-striped">
                           <thead >
                            <tr style={{"border":"solid 2px yellow"}}>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>ลำดับ</td>
                                <td style={{"width":"10%","border":"solid 2px yellow","textAlign":"center"}}>เลข</td>
                                <td style={{"width":"10%","border":"solid 2px yellow","textAlign":"center"}}>ราคา (บาท)</td>
                                <td style={{"width":"10%","border":"solid 2px yellow","textAlign":"center"}}>การแทง</td>
                                <td style={{"width":"10%","border":"solid 2px yellow","textAlign":"center"}}>สถานะ</td>
                                <td style={{"width":"10%","border":"solid 2px yellow","textAlign":"center"}}>วันที่</td>
                                <td style={{"width":"15%","border":"solid 2px yellow","textAlign":"center"}}>เวลา</td>
                                <td style={{"width":"10%","border":"solid 2px yellow","textAlign":"center"}}>การจ่าย</td>
                                <td style={{"width":"30%","border":"solid 2px yellow","textAlign":"center"}}>การจัดการ</td>
                            </tr>
                           </thead>
                           <tbody>
                            <tr >
                                <td  style={{"border":"solid 2px yellow","textAlign":"center"}}>1</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center"}}>20</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center"}}>20</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center"}}>บน</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center"}}>ถูกรางวัล</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>20/04/2554</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>10.55.00 น</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>จ่ายแล้ว</td>
                                <td style={{"border":"solid 2px yellow"}}>
                                    <div className="allbuttom">
                                        <div  className="buttom1">
                                        <button type="button" className="btn btn-light"  onClick={()=>setIsOpenPayMentModal(true)}>แก้ไขการจ่าย</button>
                                        </div>
                                   
                                    <div className="buttom2"> <button type="button" className="btn btn-light"  onClick={()=>setInfoUserModal(true)}>ข้อมูล</button></div>
                                    </div>
                                    
                                     
                                </td>
                            </tr>
                            <tr >
                                <td  style={{"border":"solid 2px yellow","textAlign":"center"}}>2</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center"}}>20</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center"}}>20</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center"}}>บน</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center"}}>ถูกรางวัล</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>20/04/2554</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>10.55.00 น</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>ยังไม่จ่าย</td>
                                <td style={{"border":"solid 2px yellow"}}>
                                    <div className="allbuttom">
                                        <div  className="buttom1">
                                        <button type="button" className="btn btn-light"  onClick={()=>ValidityState()}>เพิ่มข้อมูล</button>
                                        </div>
                                   
                                    <div className="buttom2"> <button type="button" className="btn btn-light"  onClick={()=>ValidityState()}>เพิ่มข้อมูล</button></div>
                                    </div>
                                    
                                     
                                </td>
                                
                            </tr>
                            <tr >
                                <td  style={{"border":"solid 2px yellow","textAlign":"center"}}>3</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center"}}>20</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center"}}>20</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center"}}>บน</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center"}}>ถูกรางวัล</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>20/04/2554</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>10.55.00 น</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>ยังไม่จ่าย</td>
                                <td style={{"border":"solid 2px yellow"}}>
                                    <div className="allbuttom">
                                        <div  className="buttom1">
                                        <button type="button" className="btn btn-light"  onClick={()=>ValidityState()}>เพิ่มข้อมูล</button>
                                        </div>
                                   
                                    <div className="buttom2"> <button type="button" className="btn btn-light"  onClick={()=>ValidityState()}>เพิ่มข้อมูล</button></div>
                                    </div>
                                    
                                     
                                </td>
                            </tr>
                           </tbody>
                        </table> 
                </div>
       
                <NumberModal  onClose={(e) => setpopup(e)}  show={popup} Data={setData} status={submitData}  />

                {
                    luckyModal && <InsertNumberModal onClose = {(e)=>setLuckyModal(e)}/>
                }
                {
                   isOpenPaymentModal &&   <PaymentStatusModal onClose={(e) => setIsOpenPayMentModal(e)} />
                }
                {
                  isOpenInfoUserModal &&   <InfoUserModal onClose={(e) => setInfoUserModal(e)}/>
                }
            </div>
        

        </div>
    );
}

export default Lottary;