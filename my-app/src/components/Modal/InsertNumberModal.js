import React, { useState } from "react";
import "../../CSS/ModalCss/InsertNumberModal.css"
import DatePicker from "react-datepicker";
function InsertNumberModal (props)
{

 
    const [month,setMonth] = useState([1,2,3,4,5,6,7,8,9,10,11,12])
    const [DateMonth, setDateMonth] = useState(new Date());
    const [luckyNumber, setLuckyNumber] = useState({
        threetop: "", 
        threedown: "",
        twotop: "",
        twodown:"",
        date:""
      
    });
    
    const monthjsx = 
    (
        month.map(data =>{
            return <option key={data}>{data}</option>
         })
    )
    const handleChange = (e) => {
        const value = e.target.value;
        setLuckyNumber({...luckyNumber,[ e.target.name]: value});
    }
    const handleSaving = (e)=>{
       
        luckyNumber.date = (DateMonth.getFullYear()+"-"+(1+Number(DateMonth.getMonth()))+"-"+DateMonth.getDate());
        //console.log(luckyNumber)
        props.handleSaving(e.target.name,luckyNumber)
    }
   
    return(
        <div className="luckynumber" >
        <div className="modal-header" style={{"textAlign":"center"}} ><h4>Modal title</h4></div>
        <div className="boxbody">
            <div style={{"marginLeft":"10px","marginRight":"20px"}}>
            <div className="text">  <label>วันที่</label></div>
            <div>    <DatePicker className="form-control "  name = "date" selected={DateMonth}  onChange={(date) => setDateMonth(date)} /></div>
            <div className="text">  <label>เลขหน้า 3 ตัว</label></div>
            <div>  <input type="text" className="form-control "   name = "threetop"  onChange={(e)=>handleChange(e)}/></div>
            <div className="text">  <label>เลขท้าย 3 ตัว</label></div>
            <div>  <input type="text" className="form-control "   name = "threedown"  onChange={(e)=>handleChange(e)}/></div>
            <div className="text">  <label>เลขท้าย 2 ตัว</label></div>
            <div>  <input type="text" className="form-control "   name = "twotop"  onChange={(e)=>handleChange(e)}/></div>
            <div className="text">  <label>เลขท้าย 2 ตัว</label></div>
            <div>  <input type="text" className="form-control "   name = "twodown"  onChange={(e)=>handleChange(e)}/></div>
        
            </div>
        </div>
        <div className="modal-footer" style={{"marginTop":"20px"}}>
        <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" name = "notsave" onClick={(e) =>handleSaving(e) }>Close</button>
        <button type="button" className="btn btn-primary" name = "save" onClick={(e)=>handleSaving(e)}>Save changes</button>
    </div>
</div>
    );
}
export default InsertNumberModal;