import React from "react";
import "../CSS/ContactUs.css"
import line from "../Icons/line.png"
import phone from "../Icons/telephone-call.png"
function ContactUs()
{
    return(
        <div className="boxContectUs">
            <div className="ContactUsPage">
                <div className="titleContact" style={{"textAlign":"center","marginTop":"25px"}}>
                    <h3>ข้อมูลการติดต่อ</h3>
                </div>
                <div className="tableContact" style={{"margin":"0 auto","width":"70%"}}>
                  <div className="alertbox">
                    <div className="textbox" >
                            <h3 style={{"color":"red","textShadow":"2px 2px 5px black"}}>คำเตือนโปรดอ่าน</h3>
                    </div>

                  </div>
                    <table className=""  >
                        <thead >
                           
                            <tr style={{"height":"70px"}} >
                                <td  style={{"width":"10%"}}><img src={phone} style = {{"width":"45px","height":"45px"}}/></td>
                                <td  style={{"width":"20%","fontSize":"20px"}}>เบอร์โทรติดต่อ : </td>
                                <td  style={{"fontSize":"20px"}}>&nbsp;&nbsp;&nbsp;&nbsp;0123456789</td>
                            </tr>
                            <tr  >
                                <td ><img src={line} style = {{"width":"45px","height":"45px"}}/></td>
                                <td  style={{"fontSize":"20px"}}>Line : </td>
                                <td  style={{"fontSize":"20px"}}>&nbsp;&nbsp;&nbsp;&nbsp;number-zero</td>
                            </tr>
                        </thead>
                    </table>
                </div>

            </div>
        </div>

    );
}
export default ContactUs;