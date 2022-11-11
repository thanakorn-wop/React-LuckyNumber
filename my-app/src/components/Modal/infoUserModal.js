import React from "react";
import "../../CSS/ModalCss/infoUserModal.css"
function infoUserModal(props)
{
    return(
        <div className="infoUserModal" >
        <div className="modal-header" style={{"textAlign":"center"}} ><h4>Modal title</h4></div>
        <div className="boxbody">
            <div style={{"marginLeft":"10px","marginRight":"20px"}}>
            <div className="text">  <label>ID Line :</label></div>
            <div>  <input type="text" className="form-control " value = "Number-zero"  name = "idLine" disabled/></div>
            <div className="text">  <label>Phone Number </label></div>
            <div>  <input type="text" className="form-control "  value = "0612364400"  name = "phoneNo" disabled/> </div>
            </div>
        </div>
        <div className="modal-footer" style={{"marginTop":"20px"}}>
            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={() => props.onClose(false)}>Close</button>
            {/* <button type="button" className="btn btn-primary" name = "save" onClick={(e)=>saveData(e)}>Save changes</button> */}
        </div>
</div>
    );
}
export default infoUserModal;