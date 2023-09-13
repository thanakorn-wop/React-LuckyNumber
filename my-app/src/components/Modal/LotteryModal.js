import React, { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "../../CSS/ModalCss/LotteryModal.css"
import { Await } from "react-router-dom";
import axios from "axios";
import * as urlConstant from "../Constant/UrlConstant"
import { Calendar } from "primereact/calendar";
function LotteryModal(props)
{
    const[daily,setDaily] = useState(null)
    const[newDaily,setNewDaily] = useState('')
    const [luckyItem ,setLuckyItem] = useState({date:new Date(),biglucky:"XXXXXX",threeTop:"XXX XXX",threedow:"XXX XXX",twodown:"XX",twotop:"XX"});
    const [status,setStatus] = useState(false);
    const [empty,setEmpty] = useState(false)
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
           
            const get_luckyitem = await axios.get(urlConstant.GET_LUCKYITEM,{
                headers: { 'Content-Type': 'application/json' }
            })
            if(get_luckyitem !== null && get_luckyitem !== undefined && get_luckyitem.data.datalist !== null)
            {
                // console.log("check response data = ",get_luckyitem.data);   
              
                setDateTIme({...dateTime,date:get_luckyitem.data.datalist.date});
                setLuckyItem(get_luckyitem.data.datalist)       
                setDaily(new Date(get_luckyitem.data.datalist.date))      
                console.log("new Date(post_luckyitem.data.datalist.date) = ",new Date(get_luckyitem.data.datalist.date))
            }
            else{
                setLuckyItem({date:daily,biglucky:"XXXXXX",threeTop:"XXX XXX",threedow:"XXX XXX",twodown:"XX",twotop:"XX"})
                setDaily(daily)       
                setEmpty(true)
            }
        }
        async function postitem()
        {
            // const date_format = daily.getFullYear()+"-"+(1+Number(daily.getMonth()))+"-"+daily.getDate();
            // luckyItem.date = date_format;
            const post_luckyitem = await axios.post(urlConstant.POST_LUCKYITEM,dateTime,{
                headers: { 'Content-Type': 'application/json' }
            })
            if(post_luckyitem !== null && post_luckyitem !== undefined && post_luckyitem.data.datalist !== null)
            {
               // console.log("check response data = ",post_luckyitem.data.datalist);   
                setLuckyItem(post_luckyitem.data.datalist)       
                console.log("new Date(post_luckyitem.data.datalist.date) = ",new Date(post_luckyitem.data.datalist.date))
                setDaily(new Date(post_luckyitem.data.datalist.date))       
                
            }
            else{
                setLuckyItem({date:daily,biglucky:"XXXXXX",threeTop:"XXX XXX",threedow:"XXX XXX",twodown:"XX",twotop:"XX"})
           //     console.log("luckyitem = ",luckyItem)
                setDaily(daily)       
                setEmpty(true)
                
            }
        }
     //   console.log("status = ",status)
        if(!status)
        {
             getitem()
        }
        else{
          //  console.log("check change")
            postitem()
            setStatus(false)
        }

    },[newDaily])
    function HandleDate(date)
    {
        let date_format = date.value
        // console.log("checkdate = ",date.value)
        // console.log("checkdate = ",date.target.value)
        setDaily(date_format)
        setStatus(true)
        setNewDaily(date_format)
        date_format = date_format.getFullYear()+"-"+(1+Number(date_format.getMonth()))+"-"+date_format.getDate();
        setDateTIme({...dateTime,date:date_format});
    }
    function SaveData(status)
    {
   
        props.onSave(dateTime,status,empty,setEmpty)
       
    }
    return(
        <div className="lottaryModal" >
        <div className="modal-header" style={{"borderBottom":"solid gray"}} >
            <div className="header-title" style={{"padding":"15px","color":"white"}}><h4>ตรวจสลากกินแบ่งรัฐบาล</h4></div>
        </div>
        <div className="boxbody">
            <div className="mini-box" style={{"display":"flex","flexDirection":"column"}}>
                <div  className ="dateLottary"style={{"display":"flex","flexDirection":"row","width":"100%"}} >
                    <div style={{"width":"100%","alignItems":"center","color":"white"}}>
                        <label className="date">งวดประจำวันที่</label>
                        <Calendar  value={daily} dateFormat="dd/mm/yy" style={{"marginLeft":"20px"}}   onChange={(date) => HandleDate(date) } /> 
                   
                    </div>
                    
                       
                
                </div>
                <div className="table-lottery">
                    <table className="table table-bordered table-striped" style={{"color":"white"}}>
                        <tbody>
                        <tr>
                            <td><span className="textLotteryModal" style={{"color":"white"}}>รางวัลที่ 1 รางวัลละ 6,000,000 บาท</span></td>
                            <td><span className="textLotteryModal" style={{"color":"#99FF99"}}>{luckyItem.biglucky}</span></td>
                        </tr>
                        <tr>
                            <td><span  className="textLotteryModal"style={{"color":"white"}}> 3 ตัวบน</span></td>
                            <td><span  className="textLotteryModal" style={{"color":"#99FF99"}} > {luckyItem.threeTop}</span></td>
                        </tr>
                        <tr>
                            <td><span  className="textLotteryModal"style={{"color":"white"}}>3 ตัวล่าง</span></td>
                            <td><span  className="textLotteryModal" style={{"color":"#99FF99"}}>{luckyItem.threedow}</span></td>
                        </tr>
                        <tr>
                            <td><span  className="textLotteryModal"style={{"color":"white"}}>2 ตัวบน</span></td>
                            <td><span  className="textLotteryModal" style={{"color":"#99FF99"}}>{luckyItem.twotop}</span></td>
                        </tr>
                        <tr>
                            <td><span  className="textLotteryModal"style={{"color":"white"}}>2 ตัวล่าง</span></td>
                            <td><span  className="textLotteryModal" style={{"color":"#99FF99"}}>{luckyItem.twodown}</span></td>
                        </tr>
                    </tbody>

                    </table>
                </div>
            </div>
        </div>
        <div className="modal-footer" style={{"marginTop":"15px","marginBottom":"15px","borderTop":"solid gray"}}>
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