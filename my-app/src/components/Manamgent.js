import React, { useEffect, useState } from "react";
import "../CSS/Management.css"
import { Await } from "react-router-dom";
import axios from "axios";
import * as urlConstanst from "../components/Constant/UrlConstant"
import PaginatedItems from "../components/PaginatedItems"
function Management()
{
    const [user,setUser] = useState([]);
    const [checkBox,setCheckBox] = useState(false);
    const [userInfo,setUserinfo] = useState("");
    const [isCheck,setIsCheck] = useState(null);
    const [newItem,setNewItem] = useState([]);

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
        async function getAllUser()
        {
            console.log("useEffect checkbox")
            const respon = await axios.get(urlConstanst.GET_USER,{
                headers: { 'Content-Type': 'application/json' }
            })
            if(respon.data !== null && respon.data !== "" && respon.data !== undefined)
            {
                console.log("respon.data = ",respon.data)
                setUser(respon.data.datalist)
            }
            setIsCheck("")
        }
        getAllUser()

    },[isCheck])

    async function getValueCheckBox(event)
    {
        console.log("check event = ",event.target.checked)
        const checked = event.target.checked;
        let index = event.target.id;
        const lock = "L";
        const unLock = "A"
        index = index.split(" ")
        console.log("isCheck = ",isCheck)
        if(checked)
        {
            await  postLockUser(index[1],unLock)
            setIsCheck(true)
            console.log("after function")
        }
        else{
            await postLockUser(index[1],lock)
            setIsCheck(false)
            console.log("after function lock")
        }
        
       
    }
    async function postLockUser(index,status)
    {
        user[index]["status"] = status;
        try{
          
            const response = await axios.post(urlConstanst.POST_LOCK_USER,user[index],{
                headers: { 'Content-Type': 'application/json' }
            })
            // console.log("check status = ",response.status)
            if(response.status == 200)
            {
                if(response.data !== null && response.data !== undefined && response.data !=="")
                {
                    console.log("response data = ",response.data)
                    // console.log("check response status = ",isCheck)
                }
            }
        }
        catch(error)
        {
            console.log("check error ",error)
        }
    }
    return(
        <div className="containerManagement">
            <div className="boxManagement">
                <div className="headerManagement">
                    <h3>การจัดการผู้ใช้งาน</h3>
                </div>
            <div className="tableManagement" style={{"marginTop":"20px"}}>
               <table className="table table-bordered table-striped" style={{"textAlign":"center","width":"100%"}}>
                <thead >
                    <tr style={{"border":"none"}} >
                        <td style={{"width":"50%","border":"none"}}>ชื่อเล่น</td>
                        <td style={{"border":"none"}} >ล็อคผุ้ใช้งาน</td>
                    </tr>
                </thead>
                <tbody >
                    {
                        newItem.map((data,index)=>{
                            return(
                                <tr style={{"border":"none"}}  key={data.nickname}>
                                <td style={{"border":"none"}}><label>{data.nickname}</label></td>
                                <td  style={{"border":"none"}}> 
                                <span className="switch">
                                <input type="checkbox" id={data.nickname+" "+index} className={data.nickname+" "+index} checked={data.status==="A"?true:false} name={data.nickname+" "+index} onChange={getValueCheckBox}   />
                                 <label for = {data.nickname+" "+index} ></label>
                                </span>
                                </td>
                             
                            </tr>
                            )
                        })
                    }
                </tbody>
               </table>
           
                      <div className="paginatManage">
                      <PaginatedItems items = {user} callBackItem ={(items)=>setNewItem(items) } />
                      </div>
                  
            </div>
                  
            </div>

        </div>
    );
}
export default Management;