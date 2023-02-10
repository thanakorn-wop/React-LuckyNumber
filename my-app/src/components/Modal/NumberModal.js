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
        setUserData({...userData,[ e.target.name]: value});
        // console.log("value = ",value)
    }
    const Handlesaving = (isOpen)=>{
        // console.log("check number Modal = "+e);
        // props.Data(userData)
       
        // props.status(e.target.name)
        userData.date = (DateMonth.getFullYear()+"-"+(1+Number(DateMonth.getMonth()))+"-"+DateMonth.getDate());
        props.handleSavingNum(isOpen,userData)

        console.log("check data = ",userData)
    }
   
    return(
        <div className="boxmodal">
                <div className="modal-header" ><h4>Modal title</h4></div>
                <div className="boxbody"  style={{"marginLeft":"15px","marginRight":"20px"}}>
                   
                    <div className="flexcontainer1">
                        <div className="text_numpagedate" style={{"width":"50%"}}>
                            <div className="text_numpage">  <label>วันที่</label></div>
                            <div className="datepicker" style={{"width":"60%"}}><DatePicker className="form-control "   selected={DateMonth}  onChange={(date) => setDateMonth(date)} /> </div>
                        </div>
                    
                        <div className="text_numpagechoice" style={{"width":"50%"}}>
                            <div className="text_numpage">  <label>การแทง</label></div>
                            <div className="choice" style={{"text_numpageAlign":"center"}}> 
                                <select className="form-select form-select-sm "  name = "option"   style={{"width":"60%","text_numpageAlign":"center"}} onChange={(e)=>handleChange(e)}>
                                    <option value="empty">เลือก</option>
                                    <option value="top">บน</option>
                                    <option value = "button">ล่าง</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div className="flexcontainer2">
                        <div className="text_numpageno" style={{"width":"50%"}}>
                            <div className="text_numpage">  <label>เลข</label></div>
                            <div>  <input type="text_numpage" className="form-control " style={{"width":"60%"}}  name = "number"  onChange={(e)=>handleChange(e)}/></div>
                        </div>
                        <div className="text_numpageprice2"  style={{"width":"50%"}}>
                            <div className="text_numpage">  <label>ราคา (บาท)</label></div>
                            <div>  <input type="text_numpage" className="form-control price " style={{"width":"60%"}} name = "price"  onChange={(e)=>handleChange(e)}/></div>
                        </div>

                    </div>
                   
                    <div className="flexcontainer3" >
                        <div className="text_numpageline" style={{"width":"50%"}}>
                            <div className="text_numpage">  <label>ID Line:</label></div>
                            <div>  <input type="text_numpage" className="form-control "  style={{"width":"60%"}} name = "idLine"  onChange={(e)=>handleChange(e)}/></div>
                        </div>
                        <div className="text_numpagephone" style={{"width":"50%"}}>
                            <div className="text_numpage">  <label>เบอร์โทรติดต่อ</label></div>
                            <div>  <input type="text_numpage" className="form-control " style={{"width":"60%"}} name = "phoneNumber"  onChange={(e)=>handleChange(e)}/></div>
                        </div>
                    </div>
                    
                  
                  
                </div>
                <div className="modal-footer" style={{"marginTop":"20px","marginLeft":"15px","marginBottom":"10px"}}>
                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={() => Handlesaving(false)}>Close</button>
                <button type="button" className="btn btn-primary" style={{"marginLeft":"10px"}} name = "save" onClick={(e)=>Handlesaving(true)}>Save changes</button>
            </div>
        </div>

    );

}
export default NumberModal;