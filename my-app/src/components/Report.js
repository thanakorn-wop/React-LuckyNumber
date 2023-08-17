import React, { useEffect, useState,useRef, startTransition } from "react";
import "../CSS/Report.css"
import "primereact/resources/themes/lara-light-indigo/theme.css";     
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { InputText } from "primereact/inputtext";
import "primereact/resources/primereact.min.css";     
import axios, { all } from "axios";
import * as urlConstant from "../components/Constant/UrlConstant"
import imgAccept from "../Icons/accept.png"
import imgDelete from "../Icons/delete.png"
import {useMessage} from './Constant/useMessage'
import Loading from "./Constant/Loading";
import { Messages } from 'primereact/messages'; 
import { Calendar } from 'primereact/calendar';
import { Dropdown } from 'primereact/dropdown';
import InsertLuckyNumberModal from "./Modal/InsertLuckyNumberModal";
function Report()
{
    const msgs = useRef(null);
    const blockRef = useRef(null)
    const addMessage = useMessage();
    const [allUser,setAllUser] = useState([]);
    const [dataSearch,setDataSearch] = useState({name:"",statusTransfer:"",date:"",timeTransfer:""});
    const [isOpenLuckNumberModal,setIsOpenLuckyNumberModal] = useState(false);
    const [DataLuckyNumber,setDataLuckyNumber] = useState();
    const [luckyNumber, setLuckyNumber] = useState({
        threetop: "", 
        threedown: "",
        twotop: "",
        twodown:"",
        lucktime:"",
        biglucky:"",
      
    });
   // console.log("dataSearch = ",dataSearch)
    let session =  sessionStorage.getItem("token");
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
            async function getTransferUser()
            {
              try{
                const response = await axios.get(urlConstant.GET_TRANSFER_USER,{
                    headers: { 'Content-Type': 'application/json' }
                })
                console.log("response 1= ",response.data.datalist)
                if(response.status === 200 && response.data !== null )
                {
                    console.log("response = ",response.data.datalist)
                    setAllUser(response.data.datalist)
                }
              }
              catch(Error)
              {
                    console.error("check Error ",Error);
              }
            }
             getTransferUser()
    },[])

    const imgBody = (data)=>{
       // console.log("datatable data = ",data)
        return <img src ={data.statusTransfer ==="Y"?imgAccept:imgDelete} alt={data.statusTransfer ==="Y"?"accept":"noAccept"} style={{"width":"25%"}}/>
    }
    const SearchData = ()=>{
        let date = new Date();
        date = dataSearch.date;
        console.log("data = ",dataSearch)
        if(date != '')
        {
            date =(date.getFullYear()+"-"+String(1+Number(date.getMonth())).padStart(2, '0')+"-"+String(date.getDate()).padStart(2, '0'));
        }
     
        console.log("check = ", dataSearch.name === '')
        // console.log("search data ",dataSearch)
        if(dataSearch.statusTransfer !== "" && dataSearch.date !== '' && dataSearch.name === '')
        {
            // console.log("Alluser = ",date)
            setAllUser(allUser.filter(data => data.date === date && data.statusTransfer === dataSearch.statusTransfer.status ))
        }
        else if(dataSearch.statusTransfer == "" && dataSearch.date !== '' && dataSearch.name !== '')
        {
            // console.log("Alluser = ",allUser)
            setAllUser(allUser.filter(data => data.date === date && data.nickname === dataSearch.name ))
        }
       
        else if(dataSearch.statusTransfer !== "" && dataSearch.date === '' && dataSearch.name !== '')
        {
            console.log("check4")
            setAllUser(allUser.filter(data => data.statusTransfer === dataSearch.statusTransfer.status && data.nickname === dataSearch.name ))
        }

        else if(dataSearch.statusTransfer == "" && dataSearch.date == '' && dataSearch.name !== '')
        {
            // console.log("Alluser = ",allUser)
            setAllUser(allUser.filter(data =>  data.nickname === dataSearch.name ))
        }
       
        else if(dataSearch.statusTransfer !== "" && dataSearch.date === '' && dataSearch.name === '')
        {
            console.log("check4")
            setAllUser(allUser.filter(data => data.statusTransfer === dataSearch.statusTransfer.status ))
        }
        else if(dataSearch.statusTransfer === "" && dataSearch.date !== '' && dataSearch.name === '')
        {
            console.log("check4")
            setAllUser(allUser.filter(data => data.date === date ))
        }
        else{
            setAllUser(allUser.filter(data => data.statusTransfer === dataSearch.statusTransfer.status && data.date === date && data.nickname === dataSearch.name ))
        }
        setDataSearch({...dataSearch,name:"",date:"",statusTransfer:""})

    }
    async function HandleInsertLuckyNumber(statusSaving,dataLucky,setLuckyNumber)
    {   
       
        // console.log("dataLuck1y = ",dataLucky)
        // console.log("dataLucky.threetop.length = ",dataLucky.threetop.length)
        let threeDownSplit = dataLucky.threedown.trim().split(" ")
         console.log("checkthreeDown = ",threeDownSplit.length !==4 )
        if(statusSaving === "Yes")
        {  // todo validate field  of Insert LuckyNumber Model
            if(dataLucky.threetop === null || dataLucky.threetop === undefined || dataLucky.threetop === "" )
            {
                alert("กรุณาใส่เลข เลขหน้า 3 ตัว");  
            }
            else if(dataLucky.threedown === null || dataLucky.threedown === undefined || dataLucky.threedown === "" )
            {
                alert("กรุณาใส่เลข เลขหท้าย 3 ตัว");
            }
            else if(dataLucky.twotop === null || dataLucky.twotop === undefined || dataLucky.twotop === "" )
            {
                alert("กรุณาใส่เลขเลขท้าย 2 ตัว");
            }
            else if(dataLucky.twodown=== null || dataLucky.twodown === undefined || dataLucky.twodown === "" )
            {
                alert("กรุณาใส่เลข เลขท้าย 2 ตัว");
            }
            else{
                console.log("dataLucky.threetop.length >3 || dataLucky.threetop.length <2 = ", dataLucky.threetop.length <2)
                if(Number(dataLucky.threetop.length) !==3  )
                {
                 
                    alert("กรุณาใส่เลขให้ครบ3หลักของสามตัวบน")
                    
                    
                }
                else if(threeDownSplit.length !==4  )
                {
                    alert("กรุณาใส่เลขให้ครบ4หลักของสามตัวล่าง")
                    
                }
                else if(Number(dataLucky.twotop.length) !==2 )
                {
                    alert("กรุณาใส่เลขให้ครบ2หลักของสองตัวบน")
                   
                }
                else if(Number(dataLucky.twodown.length) !==2)
                {
                    alert("กรุณาใส่เลขให้ครบ2หลักของสองตัวล่าง")
                    
                }
                else{
                    try{
                        console.log("dataLucky = ",dataLucky)
                        const respon = await axios.post(urlConstant.POST_INSERT_LUCKY_NUMBER,dataLucky,{
                            headers: { 'Content-Type': 'application/json' }
                        })
                        console.log("respon = ",respon)
                        let message = respon.data.message;
                        let process =respon.data.statusProcess;

                        // todo validate status insert from back end 
                        if(process)
                        {
                            blockRef.current.block()
                            setTimeout(() => {
                            msgs.current.clear();
                        // setMsgWaring("ทำรายการสำเร็จ");
                            blockRef.current.unBlock()
                            // setIsOpenLotteryModal(false)
                            addMessage(msgs,process,<b>{message}</b>)
                            }, 500);
                        }
                        else{
                            blockRef.current.block()
                            setTimeout(() => {
                            msgs.current.clear();
                        // setMsgWaring("ทำรายการสำเร็จ");
                            blockRef.current.unBlock()
                            // setIsOpenLotteryModal(false)
                            addMessage(msgs,process,<b>{message}</b>)
                            }, 500);
                        }
                        setIsOpenLuckyNumberModal(false)
                        setLuckyNumber({...dataLucky,threetop:"",threedown:"",twotop:"",twodown:"",lucktime:"",biglucky:""})
                        setDataSearch({...dataSearch,name:"",date:"",statusTransfer:""})
                    
                    }catch(error)
                    {
                        alert("ERROR is "+error.message)
                    }
                   
                }
            }
            
        }
        else{
            setIsOpenLuckyNumberModal(false)
            setLuckyNumber({...dataLucky,threetop:"",threedown:"",twotop:"",twodown:"",lucktime:"",biglucky:""})
            setDataSearch({...dataSearch,name:"",date:"",statusTransfer:""})
        }
        //* close InsertLucky Number Model  and set default value*/
       
    }
    const optionTransfer = [
        {
            status:"Y"
        },
        {
            status:"N"
        }
    ]
    return(
        <div className="containerReport">
        <div className="boxReport">
            <div className="headerReport" style={{"color":"white"}}>
                <h3>รายระเอียดการส่งงวด</h3>
            </div>
            <div className="listitem">
            <Loading ref={blockRef}/>
                    <Messages ref={msgs} />
                        <table className="table table-bordered table-striped">
                            <thead className="table-secondary">
                                <tr className="table-listitem">
                                    <td colSpan="4" style={{"color":"black"}}>รายการค้นหา</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr className="table-listitem" >
                                <td style={{"color":"white","width":"10%"}}><div className="textCenterTable"><span>ชื่อ</span></div></td>
                                    <td style={{"width":"30%"}}>  

                                    <div className="card flex justify-content-center">
                                        <InputText value={dataSearch.name} onChange={(e) => setDataSearch({...dataSearch,name:e.target.value})} />
                                    </div>
                                    {/* value={value} onChange={(e) => setValue(e.target.value)} */}
                                    </td>
                                    <td  style={{"color":"white","width":"15%"}} ><div className="textCenterTable"><span>สถานะการส่งยอด</span></div></td>
                                    <td>  
                                        <div className="card flex justify-content-center" style={{"width":"35%"}} >
                                            <Dropdown value={dataSearch.statusTransfer} onChange={(e) => setDataSearch({...dataSearch,statusTransfer:e.value})} options={optionTransfer} optionLabel="status" 
                                            showClear placeholder="Select status" className="w-full md:w-14rem"  />
                                        </div>
                                    </td>
                                </tr>
                                <tr className="table-listitem" style={{"color":"white","alignItems":"center"}}>
                                    <td><div className="textCenterTable"><span>งวดวันที่</span></div></td>
                                    <td colSpan={"3"}>   
                                    <Calendar value={dataSearch.date} onChange={(e) => setDataSearch({...dataSearch,date:e.value})} dateFormat="dd/mm/yy"   /> 
                                        {/* <div className="no" ><input type="number" className="form-control" onChange={(data)=>setNo(data.target.value)}/> </div>  */}
                                    </td>
                                  
                                </tr>
                                {/* **comment for find number if  i want to use it , can uncomment */}
                                {/* <tr className="table-listitem" style={{"border":"solid white 1px"}} >
                                    <td ><label>เลข</label></td>
                                    <td><input className = "form-control" type="text"/></td>
                                </tr> */}
                                <tr className="table-listitem" style={{"border":"none"}}>
                                    <td  colSpan="4" style={{"borderRight":"none","borderLeft":"none"}}>
                                       <div style={{"display":"flex"}}>
                                       <button type="button" className="btn btn-primary" onClick={()=>SearchData()}>ค้นหา</button> 
                                        <button type="button" className="btn btn-warning" style={{"marginLeft":"10px"}} onClick={()=>setIsOpenLuckyNumberModal(true)}>เลขที่ออก</button> 
                                       </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
        <div className="tableReport" style={{"marginTop":"20px"}} >
            <DataTable value={allUser} paginator rows={5}  showGridlines  rowsPerPageOptions={[5, 10, 25, 50]} tableStyle={{ width: '80%',margin:"0 auto"}}>
                <Column field="nickname" header="ชื่อ" style={{ width: '10%' }} alignHeader={"center"}  align={"center"}></Column>
                <Column field="totalPurchase" header="ยอดขายทั้งหมด" style={{ width: '10%' }} alignHeader={"center"} align={"center"} ></Column>
                <Column field="totalLost" header="ยอดคนถูก" style={{ width: '10%' }} alignHeader={"center"}align={"center"}  ></Column>
                <Column field="balance" header="กำไรสุทธิ" style={{ width: '10%' }} alignHeader={"center"}align={"center"} ></Column>
                <Column field="date" header="งวดวันที่" style={{ width: '10%' }} alignHeader={"center"}   align={"center"}></Column>
                <Column field="timeTransfer" header="วันที่ส่งยอด" style={{ width: '10%' }} alignHeader={"center"}   align={"center"}></Column>
                <Column body={imgBody} header="สถานะการส่งงวด" style={{ width: '15%' }} alignHeader={"center"}align={"center"} ></Column>
               
             
            </DataTable>
              
        </div>
              
        </div>
        {
            // ! no use  this modal , if you want to use this , should  uncomment เลขถูก 
                isOpenLuckNumberModal && <InsertLuckyNumberModal  handleSaving = {(status_saving,DataLuckyNumber,setLuckyNumber)=>HandleInsertLuckyNumber(status_saving,DataLuckyNumber,setLuckyNumber)}/>
            }

    </div>
    );
}
export default Report;