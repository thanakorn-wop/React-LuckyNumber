import React, { useState } from "react";
import "../../CSS/ModalCss/InsertNumberModal.css"
import DatePicker from "react-datepicker";
import { Calendar } from 'primereact/calendar';
function InsertLuckyNumberModal (props)
{

 
    const [month,setMonth] = useState([1,2,3,4,5,6,7,8,9,10,11,12])
    const [DateMonth, setDateMonth] = useState(new Date());
    const [luckyNumber, setLuckyNumber] = useState({
        threetop: "", 
        threedown: "",
        twotop: "",
        twodown:"",
        date:"",
        biglucky:"",
      
    });
    //console.log("check ",luckyNumber);
    const monthjsx = 
    (
        month.map(data =>{
            return <option key={data}>{data}</option>
         })
    )
    const updateDate = (event)=>{
        console.log("event = ",event.value)
        let date = event.value;
        if(date !== null)
        {
        setDateMonth(date)
        date = date.getFullYear()+"-"+(1+Number(date.getMonth()))+"-"+date.getDate();
        setLuckyNumber({...luckyNumber,date:date})

        }
        console.log("date = ",date)
      
    }
    const handleChange = (e) => {
        const value = e.target.value;
        setLuckyNumber({...luckyNumber,[ e.target.name]: value});
    }
    const handleSaving = (e)=>{
        props.handleSaving(e.target.name,luckyNumber,setLuckyNumber)
    }
   
    return(
        <div className="luckynumber" >
        <div className="modal-header" style={{"textAlign":"center"}} ><h4 style={{"padding":"20px"}}>รางวัลที่ออก</h4></div>
        <div className="boxbody">
            <div style={{"marginLeft":"10px","marginRight":"20px"}}>
            <div className="text">  <label>วันที่</label></div>
            <div className="formCalendar">    <Calendar className="" name = "date" value={DateMonth} style={{"width":"50%"}}  onChange={(e)=>updateDate(e)} /></div>
            <div className="text">  <label>รางวัลที่ 1</label></div>
            <div className="formtext">  <input type="textbox" className="form-control "   style={{"width":"50%"}} name = "biglucky"  onChange={(e)=>handleChange(e)}/></div>
            <div className="text">  <label> 3 ตัวบน</label></div>
            <div className="formtext">  <input type="textbox" className="form-control "   style={{"width":"50%"}} name = "threetop"  onChange={(e)=>handleChange(e)}/></div>
            <div className="text">  <label> 3 ตัว ล่าง</label></div>
            <div className="formtext">  <input type="textbox" className="form-control " style={{"width":"50%"}}  name = "threedown"  onChange={(e)=>handleChange(e)}/></div>
            <div className="text">  <label> 2 ตัวบน</label></div>
            <div className="formtext">  <input type="textbox" className="form-control "  style={{"width":"50%"}} name = "twotop"  onChange={(e)=>handleChange(e)}/></div>
            <div className="text">  <label> 2 ตัวล่าง</label></div>
            <div className="formtext">  <input type="textbox" className="form-control " style={{"width":"50%"}}  name = "twodown"  onChange={(e)=>handleChange(e)}/></div>
        
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