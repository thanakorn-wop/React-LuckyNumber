import React from "react";
import "../../CSS/ModalCss/DeleteDataModal.css"
function DeleteModal(props)
{
    return(
        <div className="deleteDataModal" >
        <div className="modal-header"style={{"borderBottom":"solid gray"}} ><div className="header-title" style={{"padding":"15px"}}><h4>ลบข้อมูล</h4></div></div>
        <div className="boxbody">
            <div  className ="textDelete" style={{"marginTop":"15px","marginLeft":"15px"}}>
                <h4>คุณต้องการลบข้อมูล ใช่ หรือ ไม่ ?</h4>
            {/* <div className="text">  <label>ID Line :</label></div>
            <div>  <input type="text" className="form-control " value = "Number-zero"  name = "idLine" disabled/></div>
            <div className="text">  <label>Phone Number </label></div>
            <div>  <input type="text" className="form-control "  value = "0612364400"  name = "phoneNo" disabled/> </div> */}
            </div>
        </div>
        <div className="modal-footer" style={{"marginTop":"20px","marginBottom":"10px","borderTop":"solid gray"}}>
            <div className="footer">
                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" name = "No" onClick={(e) => props.onClose(e.target.name)}>Close</button>
                <button type="button" className="btn btn-primary"  data-bs-dismiss="modal"  style={{"marginLeft":"10px"}}  name = "Yes" onClick={(e) => props.onClose(e.target.name)}>Yes</button>
            </div>
            {/* <button type="button" className="btn btn-primary" name = "save" onClick={(e)=>saveData(e)}>Save changes</button> */}
        </div>
</div>
    );
}
export default DeleteModal;