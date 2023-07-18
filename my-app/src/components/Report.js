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
import {CSSCLASS} from "./Constant/CSSCLASS"
function Report()
{
    const msgs = useRef(null);
    const blockRef = useRef(null)
    const addMessage = useMessage();
    const [allUser,setAllUser] = useState([]);
    const [dataSearch,setDataSearch] = useState({name:"",statusTransfer:"",date:"",timeTransfer:""});
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
        console.log("date = ",date.getFullYear())
        if(date != '')
        {
            date = date.getFullYear()+"-"+(Number(1)+date.getMonth())+"-"+date.getDate();
        }
        console.log("date = ",date)

        // console.log("search data ",dataSearch)
        if(dataSearch.statusTransfer !== "" && dataSearch.date !== '' && dataSearch.name == '')
        {
            console.log("Alluser = ",allUser)
            setAllUser(allUser.filter(data => data.date === "2023-07-13" && data.statusTransfer === dataSearch.statusTransfer ))
        }

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
                                    <td  style={{"borderRight":"none","borderLeft":"none"}}>
                                        <button type="button" className="btn btn-primary" onClick={()=>SearchData()}>ค้นหา</button> 
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
        <div className="tableManagement" style={{"marginTop":"20px"}} >
            <DataTable value={allUser} paginator rows={5}  showGridlines  rowsPerPageOptions={[5, 10, 25, 50]} tableStyle={{ width: '80%',"margin":"0 auto"}}>
                <Column field="nickname" header="ชื่อ" style={{ width: '10%' }} alignHeader={"center"}  align={"center"}></Column>
                <Column field="totalPurchase" header="ยอดขายทั้งหมด" style={{ width: '10%' }} alignHeader={"center"} align={"center"} ></Column>
                <Column field="totalLost" header="ยอดคนถูก" style={{ width: '10%' }} alignHeader={"center"}align={"center"}  ></Column>
                <Column field="balance" header="ยอดคงเหลือ" style={{ width: '10%' }} alignHeader={"center"}align={"center"} ></Column>
                <Column field="date" header="งวดวันที่" style={{ width: '10%' }} alignHeader={"center"}   align={"center"}></Column>
                <Column field="timeTransfer" header="วันที่ส่งยอด" style={{ width: '10%' }} alignHeader={"center"}   align={"center"}></Column>
                <Column body={imgBody} header="สถานะการส่งงวด" style={{ width: '15%' }} alignHeader={"center"}align={"center"} ></Column>
               
             
            </DataTable>
              
        </div>
              
        </div>

    </div>
    );
}
export default Report;