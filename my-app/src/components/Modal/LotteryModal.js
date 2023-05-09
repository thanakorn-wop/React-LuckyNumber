import React, { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "../../CSS/ModalCss/LotteryModal.css"
import { Await } from "react-router-dom";
import axios from "axios";
import * as urlConstant from "../Constant/UrlConstant"
function LotteryModal(props)
{
    const[daily,setDaily] = useState(new Date())
    const [luckyItem ,setLuckyItem] = useState({date:new Date(),threeTop:"XXX XXX",threedow:"XXX XXX",twodown:"XX",twotop:"XX"});
    const [dateTime,setDateTIme] = useState({date:""});
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
        async function getitem()
        {
            const date_format = daily.getFullYear()+"-"+(1+Number(daily.getMonth()))+"-"+daily.getDate();
            luckyItem.date = date_format;
            const get_luckyitem = await axios.post(urlConstant.GET_LUCKYITEM,luckyItem,{
                headers: { 'Content-Type': 'application/json' }
            })
            if(get_luckyitem !== null && get_luckyitem !== undefined && get_luckyitem.data.datalist !== null)
            {
                console.log("check response data = ",get_luckyitem.data.datalist);   
                setLuckyItem(get_luckyitem.data.datalist)       
                setDaily(new Date(get_luckyitem.data.datalist.date))       
            }
        }
        getitem()
    },[daily])
    function SaveData(status)
    {
        const date_format = daily.getFullYear()+"-"+(1+Number(daily.getMonth()))+"-"+daily.getDate();
        dateTime.date = date_format;
        props.onSave(dateTime,status)
    }
    return(
        <div className="lottaryModal" >
        <div className="modal-header" style={{"borderBottom":"solid gray"}} ><div className="header-title" style={{"padding":"15px"}}><h4>ตรวจสลากกินแบ่งรัฐบาล</h4></div></div>
        <div className="boxbody">
            <div className="mini-box" style={{"display":"flex","flexDirection":"column"}}>
                <div  className ="dateLottary" style={{"display":"flex","flexDirection":"row","width":"100%"}}>
                    <div style={{"width":"20%","alignItems":"center"}}>
                        <label className="date">งวดประจำวันที่</label>
                    </div>
                    <div className="dateLottery">
                        <DatePicker className="form-control" selected={daily}  onChange={(date) => setDaily(date)} /> 
                    </div>
                
                </div>
                <div className="table-lottery">
                    <table className="table table-bordered table-striped">
                        <tbody>
                        <tr>
                            <td><span className="textLotteryModal">รางวัลที่ 1 รางวัลละ 6,000,000 บาท</span></td>
                            <td><span className="textLotteryModal" style={{"color":"#99FF99"}}> 843019</span></td>
                        </tr>
                        <tr>
                            <td><span  className="textLotteryModal">เลขหน้า 3 ตัว 2 รางวัลๆละ 4,000 บาท</span></td>
                            <td><span  className="textLotteryModal" style={{"color":"#99FF99"}} > {luckyItem.threeTop}</span></td>
                        </tr>
                        <tr>
                            <td><span  className="textLotteryModal">เลขท้าย 3 ตัว 2 รางวัลๆละ 4,000 บาท</span></td>
                            <td><span  className="textLotteryModal" style={{"color":"#99FF99"}}>{luckyItem.threedow}</span></td>
                        </tr>
                        <tr>
                            <td><span  className="textLotteryModal">เลขท้าย 2 ตัว 1 รางวัลๆละ 2,000 บาท</span></td>
                            <td><span  className="textLotteryModal" style={{"color":"#99FF99"}}>{luckyItem.twotop}</span></td>
                        </tr>
                    </tbody>

                    </table>
                </div>
            </div>
        </div>
        <div className="modal-footer" style={{"marginTop":"20px","marginBottom":"10px","borderTop":"solid gray"}}>
            <div className="footer">
                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" name = "No" onClick={(e) => SaveData(false)}>ยกเลิก</button>
                <button type="button" className="btn btn-primary" style={{"marginLeft":"10px"}} data-bs-dismiss="modal" name = "Yes" onClick={(e) => SaveData(true)}>ตรวจรางวัล</button> 
            </div>
        
             {/* <button type="button" className="btn btn-primary" name = "save" onClick={(e)=>saveData(e)}>Save changes</button> */}
        </div>
    </div>
    );
}
export default LotteryModal;