import React, { useEffect, useState } from "react";
import "../CSS/Management.css"
import { Await } from "react-router-dom";
import axios from "axios";
import * as urlConstanst from "../components/Constant/UrlConstant"
function Management()
{
    const [user,setUser] = useState([]);
    const [checkBox,setCheckBox] = useState(false);
    const [userInfo,setUserinfo] = useState("");

    let session =  sessionStorage.getItem("token");
    axios.interceptors.request.use(
        config =>{
          config.headers.Authorization = `Bearer ${session}`;
          return config;
        }
      )

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
        async function getAllUser()
        {
            const respon = await axios.get(urlConstanst.GET_USER,{
                headers: { 'Content-Type': 'application/json' }
            })
            if(respon.data !== null && respon.data !== "" && respon.data !== undefined)
            {
                console.log("respon.data = ",respon.data)
                setUser(respon.data.datalist)
            }
        }
        getAllUser()

    },[])

    function getValueCheckBox(event)
    {
        console.log("check event = ",event.target.checked)
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
                        user.map((data)=>{
                            return(
                                <tr style={{"border":"none"}}  key={data.nickname}>
                                <td style={{"border":"none"}}><label>{data.nickname}</label></td>
                                <td  style={{"border":"none"}}> 
                                <span className="switch">
                                <input type="checkbox" id={data.nickname} className={data.nickname}  name={data.nickname} onChange={getValueCheckBox}   />
                                 <label for = {data.nickname} ></label>
                                </span>
                                </td>
                             
                            </tr>
                            )
                        })
                    }
                </tbody>
               </table>
            </div>

            </div>

        </div>
    );
}
export default Management;