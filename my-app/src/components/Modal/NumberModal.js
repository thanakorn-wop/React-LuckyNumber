import React, { useState } from "react";
import "../../CSS/ModalCss/NumberModalCss.css"
import Calendar from 'moedim';
import "react-datepicker/dist/react-datepicker.css";
import DatePicker from "react-datepicker";
function NumberModal(props)
{
  
    // const [value, onChange] = useState(new Date());
  
    const [userData, setUserData] = useState({
        id: "",
        date: "",
        option:"",
        number:"",
        price:"",
        idLine:"",
        phoneNumber:"",


    });
    const [DateMonth, setDateMonth] = useState(new Date());
   
    // console.log("check 1 = ",closepopup);
    if(props.show !== true)
    {
        return null;
    }
 
    
    const handleChange = (e) => {
        const value = e.target.value;
        userData({...setUserData,[ e.target.name]: value});
    }
    const saveData = (e)=>{
        console.log(e.target.name)
        props.Data(userData)
        console.log("check data",userData)
        props.status(e.target.name)
    
       

        console.log("check data = ",userData)
    }
   
    return(
        <div className="boxmodal">
                <div className="modal-header" ><h4>Modal title</h4></div>
                <div className="boxbody">
                    <div style={{"marginLeft":"10px","marginRight":"20px"}}>
                    <div className="text">  <label>วันที่</label></div>
                    <div className="datepicker" style={{"color":"red"}}>   
                        <DatePicker className="form-control "   selected={DateMonth}  onChange={(date) => setDateMonth(date)} />
                    </div>
                    <div className="text">  <label>การแทง</label></div>
                    <div> 
                        <select className="form-select form-select-sm "  name = "option"   style={{"width":"15%"}} onChange={(e)=>handleChange(e)}>
                            <option value="empty
                            ">เลือก</option>
                            <option value="top">บน</option>
                            <option value = "button">ล่าง</option>
                        </select>
                    </div>
                    <div className="text">  <label>เลข</label></div>
                    <div>  <input type="text" className="form-control "   name = "number"  onChange={(e)=>handleChange(e)}/></div>
                    <div className="text">  <label>ราคา (บาท)</label></div>
                    <div>  <input type="text" className="form-control price "  name = "price"  onChange={(e)=>handleChange(e)}/></div>
                    <div className="text">  <label>ID Line:</label></div>
                    <div>  <input type="text" className="form-control "   name = "idLine"  onChange={(e)=>handleChange(e)}/></div>
                    <div className="text">  <label>เบอร์โทรติดต่อ</label></div>
                    <div>  <input type="text" className="form-control "   name = "phoneNumber"  onChange={(e)=>handleChange(e)}/></div>
                    </div>
                </div>
                <div className="modal-footer" style={{"marginTop":"20px"}}>
                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={() => props.onClose(false)}>Close</button>
                <button type="button" className="btn btn-primary" name = "save" onClick={(e)=>saveData(e)}>Save changes</button>
            </div>
        </div>

    );

}
export default NumberModal;