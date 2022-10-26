import React from "react";
import "../CSS/ContactUs.css"
function ContactUs()
{
    return(
        <div className="boxContectUs">
            <div className="ContactUsPage">
                <div className="titleContact" style={{"textAlign":"center","marginTop":"20px"}}>
                    <h3>ข้อมูลการติดต่อ</h3>
                </div>
                <div>
                    <table>
                        <thead>
                            <tr>
                                <td>เบอร์โทร : </td>
                                <td>0123456789</td>
                            </tr>
                        </thead>
                    </table>
                </div>

            </div>
        </div>

    );
}
export default ContactUs;