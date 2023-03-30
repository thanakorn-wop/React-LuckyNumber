import React, { useState } from "react";
import "../../CSS/ModalCss/EditinfoModal.css"
function EditinfoModal(props)
{
    const [cost,setconst] = useState();
    function handleChange(e)
    {
        setconst(e.target.value);
    }
    function saveData()
    {
        props.Data(cost);
        props.onClose(false)

    }
    return(
        <div className="boxmodalEdit" >
                <div className="modal-header"  ><div className="header-title"><h4>Modal title</h4></div></div>
                <div className="boxbody">
                    <div style={{"marginLeft":"10px","marginRight":"20px"}}>
                    <div className="text">  <label>จำนวนเงินทุน</label></div>
                    <div>  <input type="text" className="form-control " name = "cost" onChange={(e)=>handleChange(e)}/></div>
                    {/* <div className="text">  <label>การแทง</label></div>
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
                    <div>  <input type="text" className="form-control price "  name = "price"  onChange={(e)=>handleChange(e)}/></div> */}
                    </div>
                </div>
                <div className="modal-footer" style={{"marginTop":"20px"}}>
                   <div className="footer">
                        <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={() => props.onClose(false)}>Close</button>
                        <button type="button" className="btn btn-primary"  style={{"marginLeft":"10px"}} name = "save" onClick={()=>saveData()}>Save changes</button>
                   </div>
                </div>
        </div>

    );

}
export default EditinfoModal;