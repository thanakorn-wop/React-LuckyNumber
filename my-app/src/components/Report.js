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
    const [dateSelect,setDateSelect] = useState('');
    const [dataReport,setDataReport] = useState('');
    

    let session =  sessionStorage.getItem("token");
    if(session === null || session === undefined || session ==="")
    {
      window.location.assign("/login")
    }
    axios.interceptors.request.use(
        config =>{
          config.headers.Authorization = `Bearer ${session}`;
          return config;
        }
      )
      axios.interceptors.response.use(undefined,(error) =>{
        const {status,data,config} = error.response;
        if(status === 404)
        {
          window.location.assign("pagenotfound")
        }
        if(status === 400)
        {
            alert("BAD Request 400")
        }
        if(status ===500)
        {
          console.log("error server");
           
           window.location.assign("/internalserver")
        }
        if(status === 401)
        {
             window.location.assign("/login")
        }
        if(status ===403)
        {
          window.location.assign("/login")
        }
      })

    useEffect(()=>{
            async function GetReport()
            {
                console.log("run useEffect")
              try{
                const response = await axios.get(urlConstant.GET_REPORT,{
                    headers: { 'Content-Type': 'application/json' }   
                }
                )
                if(response !==null)
                {
                    console.log("check response = ",response)
                    setDataReport(response.data.datalist)
                    setDateMonth(new Date((response.data.datalist.date)));
                 
                }
              }catch(error)
              {
                console.error(error)
              }
                
            }
            GetReport()

    },[])
    const monthjsx = 
    (
        month.map(data =>{
            return <option key={data}>{data}</option>
         })
    )
    function searchData()
     {  
        //(String(DateMonth.getDate()).padStart(2, '0')+"-"+(1+Number(DateMonth.getMonth()))+"-"+DateMonth.getFullYear());
        let date = DateMonth.getFullYear()+"-"+(1+Number(DateMonth.getMonth()))+"-"+DateMonth.getDate();
        console.log(date)
    }
 
    
    return(
        <div className="pathreport">
            <div className="boxreport">
                <div className="title">
                    <h3>Report</h3>
                </div>
                <div className="table-search-report">  
                <table className="table table-bordered table-striped">
                            <thead className="table-secondary">
                                <tr className="table-listitem">
                                    <td colSpan="4" style={{"color":"black"}}>รายการค้นหา</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr className="table-listitem" >
                                    <td style={{"width":"20%"}}><span>งวดประจำวันที่</span></td>
                                    <td> <div className="date-report"><DatePicker className="form-control" style={{}}  selected={DateMonth}  onChange={(date) => setDateMonth(date)} /></div></td>
                          
                                </tr>
                           
                                <tr className="table-listitem" style={{"border":"none"}}>
                                    <td colSpan="4" style={{"borderRight":"none","borderLeft":"none"}}>
                                    <div className="setBtn"style={{"display":"flex","margin":"0 auto"}}>
                                        <div className=" Action-btn">
                                        <button type="button" className="btn btn-warning" onClick={()=>setIsOpen(true)}>แก้ไขข้อมูล</button>
                                        </div>
                                        {/* <div className=" Action-btn">
                                            <button type="button" className="btn btn-light"  onClick={()=>setLuckyModal(true)}>เลขถูก</button>
                                        </div> */}
                                        <div className=" Action-btn">
                                            <button type="button" className="btn btn-primary" onClick={()=>searchData()}>ค้นหา</button>
                                        </div>
                                    </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                </div>
                <div>
                    <table className="table-report " style={{"marginBottom":"15px"}}>
                        <thead>
                            <tr style={{"border":"solid",}}>
                                <td>จำนวนต้นทุน : </td>
                                <td>{dataReport.balance} บาท</td>
                                <td>ยอดขาย : </td>
                                <td>{dataReport.totalPurchase} บาท </td>
                                {/* <td>จำนวนคนถูก : </td>
                                <td>30 คน</td> */}
                            </tr>
                            <tr style={{"border":"solid",}}>
                                <td>จำนวนคนถูก : </td>
                                <td>{dataReport.peoplewin} คน</td>
                                <td>จำนวนคนไม่ถูก : </td>
                                <td>{dataReport.peoplelost} คน</td>
                            </tr>
                            <tr style={{"border":"solid"}}>
                                <td>จำนวนเงินคนที่ถูก : </td>
                                <td>{dataReport.totalLost} บาท</td>
                                <td>ยอดคงเหลือ : </td>
                                <td>{dataReport.totalPurchase - dataReport.totalLost} บาท</td>
                            </tr>
                            <tr style={{"border":"solid"}}>
                                <td>จ่ายไปแล้วเป็นจำนวน : </td>
                                <td>{dataReport.pay} บาท</td>
                                <td>ยังค้างเหลืออยู่ : </td>
                                <td>{dataReport.notpay} บาท</td>
                            </tr>
                        </thead>
                    </table>
                </div>
                {
                    isOpen && <EditinfoModal onClose = {(e) => setIsOpen(false)} Data = {(e)=>setCost(e)}/>
                }
            </div>
        </div>

    );

}
export default Report;