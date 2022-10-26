import React, { useState } from "react";
import "../../CSS/ModalCss/InsertNumberModal.css"
function InsertNumberModal (props)
{
    const [month,setMonth] = useState([1,2,3,4,5,6,7,8,9,10,11,12])
    const [luckyNumber, setLuckyNumber] = useState({
        id: "",
        threeFontNumber: "", 
        threeBackNumber: "",
        twoBackNumber: ""
      
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
        console.sole.log(luckyNumber)
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
            <div>  <input type="text" className="form-control " name = "date" onChange={(e)=>handleChange(e)}/></div>
            <div className="text">  <label>เดือน</label></div>
            <div>  
                <select>
                    <option>--เลือก--</option>
                    {monthjsx}        
                 </select>
            </div>
           
            <div className="text">  <label>เลขหน้า 3 ตัว</label></div>
            <div>  <input type="text" className="form-control "   name = "threeFontNumber"  onChange={(e)=>handleChange(e)}/></div>
            <div className="text">  <label>เลขท้าย 3 ตัว</label></div>
            <div>  <input type="text" className="form-control "   name = "threeBackNumber"  onChange={(e)=>handleChange(e)}/></div>
            <div className="text">  <label>เลขท้าย 2 ตัว</label></div>
            <div>  <input type="text" className="form-control "   name = "twoBackNumber"  onChange={(e)=>handleChange(e)}/></div>
        
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