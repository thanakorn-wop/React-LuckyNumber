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
        twodown:""
      
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
        console.log(luckyNumber)
    }
    const saveData = (e)=>{
        // console.log(e.target.name)
        // props.Data(numberData)
        // console.log("check data",numberData)
        // props.status(e.target.name)
    
       

        // console.log("check data = ",numberData)
    }
   
    return(
        <div className="luckynumber" >
        <div className="modal-header" style={{"textAlign":"center"}} ><h4>Modal title</h4></div>
        <div className="boxbody">
            <div style={{"marginLeft":"10px","marginRight":"20px"}}>
            <div className="text">  <label>วันที่</label></div>
            <div>    <DatePicker className="form-control "   selected={DateMonth}  onChange={(date) => setDateMonth(date)} /></div>
           
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
        <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={() => props.onClose(false)}>Close</button>
        <button type="button" className="btn btn-primary" name = "save" onClick={(e)=>saveData(e)}>Save changes</button>
    </div>
</div>
    );
}
export default InsertNumberModal;