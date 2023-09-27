import React, { useEffect, useState } from "react";
import "../../CSS/ModalCss/InsertNumberModal.css"
import DatePicker from "react-datepicker";
import { Calendar } from 'primereact/calendar';
import axios from "axios";
import { Checkbox } from "primereact/checkbox";
import * as urlConstant from "../Constant/UrlConstant"
function InsertLuckyNumberModal (props)
{

 
    const [month,setMonth] = useState([1,2,3,4,5,6,7,8,9,10,11,12])
    const [DateMonth, setDateMonth] = useState(new Date());
    const now = new Date(); // Create a new Date object representing the current date and time
    const [checked, setChecked] = useState(false);
    const hours = now.getHours(); // Get the current hour (0-23)
    const minutes = now.getMinutes(); // Get the current minute (0-59)
    
    // Format the hours and minutes with leading zeros if needed
    const formattedHours = hours < 10 ? `0${hours}` : hours;
    const formattedMinutes = minutes < 10 ? `0${minutes}` : minutes;
    
    const currentTime = `${formattedHours}:${formattedMinutes}`;
    // Use currentTime to set the state
    const [time, setTime] = useState(currentTime);
    const [defultNumber, setDefultNumber] = useState({
        threetop: "000", 
        threedown: "000,000,000,000",
        twotop: "00",
        twodown:"00",
        date:new Date(),
        biglucky:"000000",
        time:currentTime,
        statusLottary:checked
      
    });
    // const [luckyNumber, setLuckyNumber] = useState({
    //     threetop: "", 
    //     threedown: "",
    //     twotop: "",
    //     twodown:"",
    //     date:new Date(),
    //     biglucky:"",
    //     time:""
      
    // });
   useEffect(()=>
   {
        async function getLucky()
        {
            
            try{
                let date = DateMonth;
                date = date.getFullYear()+"-"+(1+Number(date.getMonth()))+"-"+date.getDate();
                let newDate = {date:date};

                const reponse =await axios.post(urlConstant.POST_LUCKYITEM,newDate,{
                    headers: { 'Content-Type': 'application/json' }
                })
                // console.log("reponse ",reponse)
                if(reponse.status === 200)
                {
                    let data = reponse.data.datalist;
                    if(data !== null && data !== undefined && data !== "" )
                    {
                        console.log("check response = ",data)
                         setDefultNumber({...defultNumber,threetop:data.threeTop,threedown:data.threedow,twotop:data.twotop,twodown:data.twodown,time:data.time,biglucky:data.biglucky,statusLottary:data.statusLottary ==="Y"?true:false})
                    }
                    else{

                        setDefultNumber({
                            threetop: "000", 
                            threedown: "000,000,000,000",
                            twotop: "00",
                            twodown:"00",
                            date:DateMonth,
                            biglucky:"000000",
                            time:time,
                            statusLottary:checked
                          
                        })
                    }
                }
            }catch(e)
            {
                console.error("Error is ",e)
            }
        }
        getLucky()
   },[DateMonth])
    const monthjsx = 
    (
        month.map(data =>{
            return <option key={data}>{data}</option>
         })
    )
    // console.log("defultNumber =",defultNumber)
    const updateDate = (event)=>{
        // console.log("event = ",event.value)
        let date = event.value;
        if(date !== null)
        {
        setDateMonth(date)
        // setLuckyNumber({...luckyNumber,date:date})
        setDefultNumber({...defultNumber,date:date})

        }
        // console.log("date = ",date)
      
    }
    const handleChange = (e) => {
        const value = e.target.value;
        console.log("time = ",value)
        // setLuckyNumber({...luckyNumber,[ e.target.name]: value});
        setDefultNumber({...defultNumber,[ e.target.name]: value})
       
    }
    const handleSaving = (e)=>{
        props.handleSaving(e.target.name,defultNumber,setDefultNumber)
    }
   const updateTime = (e)=>{
        const value = e.target.value;
        setDefultNumber({...defultNumber,[ e.target.name]: value})
        setTime(value)
   }
   const isCheck = (e)=>{
    const value = e
    setDefultNumber({...defultNumber,statusLottary: value})
   
    
}
//    console.log(" defultNumber = 1", defultNumber)
    return(
        <div className="luckynumber" >
        <div className="modal-header" style={{"textAlign":"center"}} ><h4 style={{"padding":"20px"}}>รางวัลที่ออก</h4></div>
        <div className="boxbody">
            <div style={{"marginLeft":"10px","marginRight":"20px"}}>
            <div className="text">  <label>วันที่</label></div>
            <div className="formCalendar">    <Calendar className="" name = "date" value={DateMonth} dateFormat="dd/mm/yy" style={{"width":"50%"}}  onChange={(e)=>updateDate(e)} /></div>
            <div className="text">  <label>เวลาที่ไม่สามารถซื้อเกินได้</label></div>
            <div className="formtext">  <input type="time" className="form-control " value = {defultNumber.time} style={{"width":"50%"}} name = "time"  onChange={(e)=>updateTime(e)}/></div>
            <div className="text">  <label>รางวัลที่ 1</label></div>
            <div className="formtext">  <input type="textbox" className="form-control " value = {defultNumber.biglucky}  style={{"width":"50%"}} name = "biglucky"  onChange={(e)=>handleChange(e)}/></div>
            <div className="text">  <label> 3 ตัวบน</label></div>
            <div className="formtext">  <input type="textbox" className="form-control "    value = {defultNumber.threetop} style={{"width":"50%"}} name = "threetop"  onChange={(e)=>handleChange(e)}/></div>
            <div className="text">  <label> 3 ตัว ล่าง</label></div>
            <div className="formtext">  <input type="textbox" className="form-control " value = {defultNumber.threedown} style={{"width":"50%"}}  name = "threedown"  onChange={(e)=>handleChange(e)}/></div>
            <div className="text">  <label> 2 ตัวบน</label></div>
            <div className="formtext">  <input type="textbox" className="form-control " value = {defultNumber.twotop}  style={{"width":"50%"}} name = "twotop"  onChange={(e)=>handleChange(e)}/></div>
            <div className="text">  <label> 2 ตัวล่าง</label></div>
            <div className="formtext">  <input type="textbox" className="form-control " value = {defultNumber.twodown} style={{"width":"50%"}}  name = "twodown"  onChange={(e)=>handleChange(e)}/></div>
            <div className="checkbox">
                <label>สถานะหวยออก</label>
                <Checkbox onChange={e => isCheck(e.checked)}  style = {{"marginLeft":"15px"}} checked={defultNumber.statusLottary}></Checkbox>
            </div>
        
            </div>
        </div>
        <div className="modal-footer" style={{"marginTop":"20px","padding":"20px"}}>
            <button type="button" className="btn btn-secondary" style={{"marginRight":"10px"}} data-bs-dismiss="modal" name = "No" onClick={(e) =>handleSaving(e) }>Close</button>
            <button type="button" className="btn btn-primary" name = "Yes" onClick={(e)=>handleSaving(e)}>Save changes</button>
        </div>
</div>
    );
}
export default InsertLuckyNumberModal;