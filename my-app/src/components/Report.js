import React, { useEffect, useState } from "react";
import "../CSS/Report.css"
import "primereact/resources/themes/lara-light-indigo/theme.css";     
    
//core
import "primereact/resources/primereact.min.css";     
import axios from "axios";
import * as urlConstant from "../components/Constant/UrlConstant"
function Report()
{
    const [allUser,setAllUser] = useState([]);
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
    return(
        <div className="containerReport">
        <div className="boxReport">
            <div className="headerReport">
                <h3>รายระเอียดการส่งงวด</h3>
            </div>
        <div className="tableManagement" style={{"marginTop":"20px"}}>
           <table className="table table-bordered table-striped" style={{"textAlign":"center","width":"80%","margin":"0 auto"}}>
            <thead >
                <tr style={{"border":"none"}} >
                    <td >ชื่อ</td>
                    <td >ยอดทั้งหมดที่ขาย</td>
                    <td >ยอดคนถูก</td>
                    <td style={{"width":"20%"}} >เหลือ</td>
                    <td>สถานะการส่งงวด</td>
                    <td  >เวลาที่ส่ง</td>
                </tr>
            </thead>
            <tbody >
                {
                    allUser.map((data,index)=>{
                        return(
                            <tr>
                                <td>{data.nickname}</td>
                            </tr>
                        )
                    })
                }
            
            </tbody>
           </table>
       
                  <div className="paginatManage">
                  {/* <PaginatedItems items = {user} callBackItem ={(items)=>setNewItem(items) } /> */}
                  </div>
              
        </div>
              
        </div>

    </div>
    );
}
export default Report;