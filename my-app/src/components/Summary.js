import React, { useEffect, useState,useRef } from "react";
import "../CSS/Summary.css"
import EditinfoModal from "./Modal/EditinfoModal";
import "../CSS/ModalCss/EditinfoModal.css"
import DatePicker from "react-datepicker";
import axios from "axios";
import * as urlConstant from "./Constant/UrlConstant"
import { Messages } from 'primereact/messages';
import {useMessage} from './Constant/useMessage'
import Loading from "./Constant/Loading";
import  AuthContext from "../components/Authen/AuthenProvider"
import { useContext } from "react";
import {useAxiosProvider} from "./Axios/useAxios";
function Report ()
{
    const {auth,setAuth} = useContext(AuthContext);
    const useAxios = useAxiosProvider()
    const msgs = useRef(null);
    const blockRef = useRef(null)
    const addMessage = useMessage();
    const [isOpen ,setIsOpen] = useState(false);
    const [cost,setCost] = useState(0);
    const [month,setMonth] = useState([1,2,3,4,5,6,7,8,9,10,11,12])
    const [reportDate, setReportDate] = useState(new Date());
    const [dateSelect,setDateSelect] = useState("last");
    const [dataReport,setDataReport] = useState({balance:0,cost:0,date:0,id:0,idSeller:0,nickname:0,notpay:0,pay:0,peoplelost:0,peoplewin:0,statusTransfer:'',totalLost:0,totalPurchase:0});
    const mykeysVAlues = window.location.search;
    const urlParams = new URLSearchParams(mykeysVAlues)
    const param = urlParams.get('date');
    console.log("param =  ",auth)

    let session =  sessionStorage.getItem("token");
    // if(session === null || session === undefined || session ==="")
    // {
    //   window.location.assign("/login")
    // }
    // let config = {
    //     headers: {
    //       "Content-Type": "application/json",
    //       'Access-Control-Allow-Origin': '*',
    //       }
    //     }
    // axios.interceptors.request.use(
    //     config =>{
    //       config.headers.Authorization = `Bearer ${auth}`;
    //       return config;
    //     }
    //   )
    //   axios.interceptors.response.use(undefined,(error) =>{
    //     const {status,data,config} = error.response;
    //     if(status === 404)
    //     {
    //       window.location.assign("pagenotfound")
    //     }
    //     if(status === 400)
    //     {
    //         alert("BAD Request 400")
    //     }
    //     if(status ===500)
    //     {
    //       console.log("error server");
           
    //        window.location.assign("/internalserver")
    //     }
    //     if(status === 401)
    //     {
    //          window.location.assign("/login")
    //     }
    //     if(status ===403)
    //     {
    //       window.location.assign("/login")
    //     }
    //   })

    useEffect(()=>{
            async function GetReport()
            {
                // console.log("run useEffect")
                // console.log("url = ",urlConstant.GET_REPORT+{dateSelect})
              try{
                const response = await useAxios.get(urlConstant.GET_REPORT+dateSelect)
                console.log("check response = ",response)
                if(response.status === 200 && response.data.datalist !==null && response.data.datalist !== undefined)
                {  
                    msgs.current.clear();
           
                    setDataReport(response.data.datalist)
                    setReportDate(new Date((response.data.datalist.date)));
                 
                }
                else{
                    blockRef.current.block()
                    setTimeout(() => {
                    msgs.current.clear();
                    // setMsgWaring("ทำรายการสำเร็จ");
                    blockRef.current.unBlock()
                    addMessage(msgs,response.data.statusMessage,<b>{response.data.message}</b>)
                }, 500);
                    setDataReport({balance:0,cost:0,date:0,id:0,idSeller:0,nickname:0,notpay:0,pay:0,peoplelost:0,peoplewin:0,statusTransfer:'',totalLost:0,totalPurchase:0})

                }
              }catch(error)
              {
                console.error(error)
              }
                
            }
            GetReport()

    },[dateSelect])
    const monthjsx = 
    (
        month.map(data =>{
            return <option key={data}>{data}</option>
         })
    )
    function searchData()
     {  
        //(String(DateMonth.getDate()).padStart(2, '0')+"-"+(1+Number(DateMonth.getMonth()))+"-"+DateMonth.getFullYear());
        let date = reportDate.getFullYear()+"-"+String(1+Number(reportDate.getMonth())).padStart(2, '0')+"-"+String(reportDate.getDate()).padStart(2, '0');
        setDateSelect(date);
    }
 
    
    return(
        
        <div className="pathreport">
            <div className="boxreport">
                <div className="title">
                    <h3>Summary</h3>
                </div>
                {/* <Messages ref={msgs} /> */}
                <Loading ref={blockRef}/>
                <div className="table-search-report">  
                <Messages ref={msgs} />
                <table className="table table-bordered table-striped"  >
                            <thead className="table-secondary">
                                <tr className="table-listitem">
                                    <td colSpan="4" style={{"color":"black"}}><span>รายการค้นหา</span></td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr className="table-listitem" >
                                    <td style={{"width":"20%","color":"white"}}><span>งวดประจำวันที่</span></td>
                                    <td> <div className="date-report"><DatePicker className="form-control"  dateFormat= "dd-MM-yyyy"   selected={reportDate}  onChange={(date) => setReportDate(date)} /></div></td>
                          
                                </tr>
                           
                                <tr className="table-listitem" style={{"border":"none"}}>
                                    <td colSpan="4" style={{"borderRight":"none","borderLeft":"none"}}>
                                    <div className="setBtn"style={{}}>
                                            <button type="button" className="btn btn-warning" onClick={()=>setIsOpen(true)}>แก้ไขข้อมูล</button>
                                            <button type="button" className="btn btn-primary" style={{"marginLeft":"5px"}} onClick={()=>searchData()}>ค้นหา</button>
                                    </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                </div>
                <div>
                    <table className="table-report " style={{"marginBottom":"15px","color":"white"}}>
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
                                <td>กำไรสุทธิ : </td>
                                <td>{dataReport.balance} บาท</td>
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