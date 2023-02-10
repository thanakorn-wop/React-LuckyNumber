import React, { useEffect, useState } from "react";
import "../CSS/Lottary.css"
import NumberModal from "./Modal/NumberModal"
import * as urlConstant from "../components/Constant/UrlConstant"
import InsertNumberModal from "./Modal/InsertNumberModal";
import PaymentStatusModal from "../components/Modal/PaymentStatusModal"
import InfoUserModal from "./Modal/infoUserModal"
import DatePicker from "react-datepicker";
import { useNavigate } from "react-router-dom";
import axios from 'axios';
function Lottary()
{


    const [popup,setpopup] =  useState(false);
    const [status,setStatus] = useState();
    const [luckyModal,setLuckyModal]  =useState(false);
    const [isOpenPaymentModal,setIsOpenPayMentModal] = useState(false);
    const [isOpenInfoUserModal,setInfoUserModal] = useState(false);
    const [DateMonth, setDateMonth] = useState(new Date());
    const [dataSet,setDataSet] = useState([]);
    const [sessionUser,setSession] = useState(sessionStorage.getItem("token"));
    const [DataLuckyNumber,setDataLuckyNumber] = useState();
    const [ newData,setNewData] = useState({
        id: "",
        date: "", 
    });
    // let navigate = useNavigate()
  
    let session =  sessionStorage.getItem("token");
    axios.interceptors.request.use(
        config =>{
          config.headers.Authorization = `Bearer ${session}`;
          return config;
        }
      )
    if(session === null || session === undefined || session ==="")
    {
      window.location.assign("/login")
    }
    useEffect(()=>{
                    axios.get(urlConstant.GET_LIST_LOTTARY,{
                        headers: { 'Content-Type': 'application/json' }
                    }).then(resp =>{
                        if(resp !== null && resp !== undefined)
                        {
                            setDataSet(resp.data);
                        }
                    })   
    },[])
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
    //todo validate the  saving status from InsertLuckyNumber model
    function HandleInsertNumber(e,DataLuckyNumber)
    {
        console.log("save InsertNUmber = ",e,DataLuckyNumber);
        if(e === "save")
        {  // todo validate field  of Insert LuckyNumber Model
            if(DataLuckyNumber.threetop === null || DataLuckyNumber.threetop === undefined || DataLuckyNumber.threetop === "" )
            {
                alert("กรุณาใส่เลข เลขหน้า 3 ตัว");
            }
            else if(DataLuckyNumber.threedown === null || DataLuckyNumber.threedown === undefined || DataLuckyNumber.threedown === "" )
            {
                alert("กรุณาใส่เลข เลขหท้าย 3 ตัว");
            }
            else if(DataLuckyNumber.twotop === null || DataLuckyNumber.twotop === undefined || DataLuckyNumber.twotop === "" )
            {
                alert("กรุณาใส่เลขเลขท้าย 2 ตัว");
            }
            else if(DataLuckyNumber.twodown=== null || DataLuckyNumber.twodown === undefined || DataLuckyNumber.twodown === "" )
            {
                alert("กรุณาใส่เลข เลขท้าย 2 ตัว");
            }
            else{
                const post_Insert_Lucky_Number =axios.post(urlConstant.POST_INSERT_LUCKY_NUMBER,DataLuckyNumber,{
                    headers: { 'Content-Type': 'application/json' }
                }).then(res=>{
                  
                    //  console.log("code = ",res)
                    let message = res.data.message;
                    let statusCode = res.data.statusCode;
                    // todo validate status insert from back end 
                    if(statusCode ==='01' && message === "success")
                    {
                        alert("ทำรายการสำเร็จ")
                        setLuckyModal(false)
                    }
                    else if(statusCode ==='01' && message === 'duplicate_data')
                    {
                        alert("มีการทำรายการซ้ำของวันที่")
                    }
                    else{
                        alert("เกิดข้อผิดพลาดในการทำรายการ")
                    }
                })
            
          //  console.log("check post insert  = ",post_Insert_Lucky_Number)

            }
        }
        //* close InsertLucky Number Model */
        else{
            setLuckyModal(false)
        }
       // console.log("show lucky number ",DataLuckyNumber)
    }
    function HandleluckyModal(isOpen,dataNum)
    {
     
        if(isOpen)
        {
            console.log(dataNum.option);
            if(dataNum.option === null || dataNum.option ===undefined || dataNum.option ==='')
            {
                alert("กรุณาเลือกการแทง");
            }
            else if(dataNum.price  === null || dataNum.price ===undefined || dataNum.price ==='' )
            {
                alert("กรุณาใส่ราคา");
            }
            else if(dataNum.number  === null || dataNum.number ===undefined || dataNum.number ==='' )
            {
                alert("กรุณาใส่เลข");
            }
            else{
                const Post_Insert_Number = axios.post(urlConstant.POST_INSERT_NUMBER,dataNum,{
                    headers: { 'Content-Type': 'application/json' }
                }).then(res =>{
                    console.log("check response num = ",res.data)
                })

            }
        }
        else{
            setpopup(false)
        }
    }
    return(
        <div className="mainpage">
            <div className="boxpage" >
                    <div className="title" style={{"textAlign":"center","marginTop":"20px"}}>
                       <h3>รายการหวย ประจำวันที่ 16/10/2565</h3>
                    </div>
                    <div className="info" style={{"marginTop":"40px"}}> 
                        <table className="tabletext" style={{"width":"50%","margin":"0 auto"}} >
                            <thead>
                                <tr  >
                                    <td style={{}} > <button type="button" className="btn btn-light"  onClick={()=>ValidityState()}>เพิ่มข้อมูล</button> </td>
                                    <td style={{}} > <button type="button" className="btn btn-light" onClick={()=>setLuckyModal(true)}>เลขถูก</button> </td>
                                    <td style={{}}>  <button type="button" className="btn btn-primary" style={{}} >ค้นหา</button>  </td>
                                    <td style={{}}>
                                        <select>
                                            <option>เลือก</option>
                                            <option>ยังไม่จ่าย</option>
                                            <option>จ่ายแล้ว</option>
                                        </select>
                                    </td>
                                    <td >
                                        <div style={{"width":"50%"}}>
                                        <DatePicker className="form-control "   selected={DateMonth}  onChange={(date) => setDateMonth(date)} />
                                        </div>
                                    </td>
                                </tr>
                            </thead>
                        </table>
                        <table style={{"margin":"20px auto"}}>
                            <thead>
                              
                                <tr>
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
                            <tr style={{"border":"solid 2px white"}}>
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
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>1</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>20</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>20</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>บน</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>ถูกรางวัล</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>20/04/2554</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>10.55.00 น</td>
                                <td   style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span className="paynow">จ่ายแล้ว</span></td>
                                <td style={{"border":"solid 2px yellow"}}>
                                    <div className="allbuttom">
                                        <div  className="buttom1">
                                        <button type="button" className="btn btn-info"  onClick={()=>setIsOpenPayMentModal(true)}>แก้ไข</button>
                                        </div>
                                   
                                    <div className="buttom2"> <button type="button" className="btn btn-light"  onClick={()=>setInfoUserModal(true)}>ข้อมูล</button></div>
                                    </div>
                                    
                                     
                                </td>
                            </tr>
                            <tr >
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>2</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>20</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>20</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>บน</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>ถูกรางวัล</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>20/04/2554</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>10.55.00 น</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span className="notpay">ยังไม่จ่าย</span></td>
                                <td style={{"border":"solid 2px yellow"}}>
                                    <div className="allbuttom">
                                        <div  className="buttom1">
                                        <button type="button" className="btn btn-light"  onClick={()=>ValidityState()}>แก้ไข</button>
                                        </div>
                                   
                                    <div className="buttom2"> <button type="button" className="btn btn-light"  onClick={()=>ValidityState()}>เพิ่มข้อมูล</button></div>
                                    </div>
    
                                </td>
                                
                            </tr>
                            <tr >
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>3</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>20</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>20</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>บน</td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>ถูกรางวัล</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>20/04/2554</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}>10.55.00 น</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span className="paynow">จ่ายแล้ว</span></td>
                                <td style={{"border":"solid 2px yellow"}}>
                                    <div className="allbuttom">
                                        <div  className="buttom1">
                                        <button type="button" className="btn btn-light"  onClick={()=>ValidityState()}>แก้ไข</button>
                                        </div>
                                   
                                    <div className="buttom2"> <button type="button" className="btn btn-light"  onClick={()=>ValidityState()}>เพิ่มข้อมูล</button></div>
                                    </div>
                                    
                                     
                                </td>
                            </tr>
                           </tbody>
                        </table> 
                     <nav aria-label="..." style={{"marginLeft":"120px","marginTop":"20px","position":"absolute","zIndex":"-1"}}>
                    <ul className="pagination" >
                        <li className="page-item disabled">
                        <a className="page-link">Previous</a>
                        </li>
                        <li className="page-item"><a className="page-link" href="#">1</a></li>
                        <li className="page-item active" aria-current="page">
                        <a className="page-link" href="#">2</a>
                        </li>
                        <li className="page-item"><a className="page-link" href="#">3</a></li>
                        <li className="page-item">
                        <a className="page-link" href="#">Next</a>
                        </li>
                    </ul>
                    </nav>
                </div>
       
                   
            </div>
            {/* // list insert purachse number  modal */}
            <NumberModal  handleSavingNum={(isOpen,dataNum) => HandleluckyModal(isOpen,dataNum)}  show={popup}   />

            {
            // todo  insert modal lottary
                luckyModal && <InsertNumberModal  handleSaving = {(status_saving,DataLuckyNumber)=>HandleInsertNumber(status_saving,DataLuckyNumber)} luckyNumber ={(e)=>setDataLuckyNumber(e)}/>
            }
            {
                // todo paymethod modal changing
            isOpenPaymentModal &&   <PaymentStatusModal onClose={(e) => setIsOpenPayMentModal(e)} />
            }
            {
                
            isOpenInfoUserModal &&   <InfoUserModal onClose={(e) => setInfoUserModal(e)}/>
            }

        </div>
    );
}

export default Lottary;