import React, { useEffect, useState } from "react";
import "../../CSS/ModalCss/NumberModalCss.css"
import Calendar from 'moedim';
import "react-datepicker/dist/react-datepicker.css";
import DatePicker from "react-datepicker";
function NumberModal(props)
{
  
    // const [value, onChange] = useState(new Date());
    const [allprice,setallprice] = useState(0);
    const [data,setdata] = useState();
    const [userData, setUserData] = useState({
        id: "",
        date: "",
        option:"",
        number:"",
        price:"",
        all_price:"",
        idLine:"",
        phoneNumber:"",
        luckytime:""


    });
    const [DateMonth, setDateMonth] = useState(new Date());
    const [LuckyDate, setLuckyDate] = useState(new Date());

    useEffect(()=>{
        console.log("check price = ",userData.price);
        let number = userData.number;
        let price = userData.price;
        number = number.trim();
        number = number.replaceAll(","," ")
        number = number.split(" ");
        let arrNo = [];
        for(let i = 0 ; i<number.length ; i++)
        {
            if(number[i] != ""){
                if(number[i].length >1 && number[i].length<4)
                {
                    if(Number(number[i]))
                    {
                        arrNo.push(number[i]);  
                    }
                   
                }
            }
        }
   
        setallprice(arrNo.length * price )

    },[userData.price,userData.number])
    // console.log("check 1 = ",closepopup);
    if(props.show !== true)
    {
        return null;
    }
 

    const handleChange = (e) => {
        const value = e.target.value;
        setUserData({...userData,[ e.target.name]: value});
        // if(Number(userData.number)){
        //     console.log(true);
        // }
        // else{
        //     console.log(false)
        // }
    
    }
    const Handlesaving = (isSave)=>{
        // console.log("check number Modal = "+e);
        // props.Data(userData)
       
        // props.status(e.target.name)
        // console.log("check date = ",String(DateMonth.getDate()).padStart(2, '0'))
        userData.date = DateMonth.getFullYear()+"-"+(1+Number(DateMonth.getMonth()))+"-"+DateMonth.getDate();
        userData.luckytime = LuckyDate.getFullYear()+"-"+(1+Number(LuckyDate.getMonth()))+"-"+LuckyDate.getDate();
        console.log("check data again = ",userData)
        props.handleSavingNum(isSave,userData)
       
        // console.log("check data = ",userData)
    }
   
    return(
        <div className="boxmodal">
                <div className="modal-header" style={{"borderBottom":"solid gray"}} ><div className="header-title" style={{"padding":"15px"}}><h4>เพิ่มข้อมูล </h4></div></div>
                <div className="boxbody"  style={{"marginLeft":"15px","marginRight":"20px"}}>
                   
                    <div className="flexcontainer1">
                        <div className="text_numpagedate" style={{"width":"50%"}}>
                            <div className="text_numpage">  <label>วันที่ซื้อ</label></div>
                            <div className="datepicker" style={{"width":"60%"}}><DatePicker className="form-control "   selected={DateMonth}  onChange={(date) => setDateMonth(date)} /> </div>
                        </div>
                    
                        <div className="text_numpagechoice" style={{"width":"50%"}}>
                            <div className="text_numpage">  <label>การแทง</label></div>
                            <div className="choice" style={{"text_numpageAlign":"center"}}> 
                                <select className="form-select form-select-sm "  name = "option"   style={{"width":"60%","text_numpageAlign":"center"}} onChange={(e)=>handleChange(e)}>
                                    <option value="empty">เลือก</option>
                                    <option value="Top">บน</option>
                                    <option value = "Below">ล่าง</option>
                                    <option value = "Tod">โต๊ด</option>
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
                            <div>  <input type="number" className="form-control price " style={{"width":"60%"}} name = "price"  onChange={(e)=>handleChange(e)}/></div>
                        </div>

                    </div>
                    {/* <div className="flexcontainer3" >
                        <div className="text_numpageline" style={{"width":"50%"}}>
                            <div className="text_numpage">  <label>ID Line:</label></div>
                            <div>  <input type="text_numpage" className="form-control "  style={{"width":"60%"}} name = "idLine"  onChange={(e)=>handleChange(e)}/></div>
                        </div>
                        <div className="text_numpagephone" style={{"width":"50%"}}>
                            <div className="text_numpage">  <label>เบอร์โทรติดต่อ</label></div>
                            <div>  <input type="text_numpage" className="form-control " style={{"width":"60%"}} name = "phoneNumber"  onChange={(e)=>handleChange(e)}/></div>
                        </div> 
                    </div> */}
                    <div className="flexcontainer4">
                        <div className="LuckyDate" style={{"width":"50%"}}>
                            <div className="text_numpage">  <label>งวดประจำวันที่</label></div>
                            <div className="datepicker" style={{"width":"60%"}}><DatePicker className="form-control "   selected={LuckyDate}  onChange={(date) => setLuckyDate(date)} /> </div>
                        </div>
                    </div>
                    <div className="flexcontainer5">
                        <div className="totalprice">
                            <h4>ยอดรวมทั้งหมด : {allprice} </h4>
                        </div>
                    </div>
                    
                  
                  
                </div>
                <div className="modal-footer" style={{"marginTop":"20px","marginBottom":"10px","borderTop":"solid gray"}}>
                    <div className="footer">
                        <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={() => Handlesaving(false)}>Close</button>
                        <button type="button" className="btn btn-primary" style={{"marginLeft":"10px"}} name = "save" onClick={(e)=>Handlesaving(true)}>Save changes</button>
                    </div>
                </div>
        </div>

    );

}
export default NumberModal;