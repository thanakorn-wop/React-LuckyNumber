import React, { useEffect, useState } from "react";
import "../../CSS/ModalCss/NumberModalCss.css"

import "react-datepicker/dist/react-datepicker.css";
import DatePicker from "react-datepicker";
import plus from "../../Icons/plus.png"
import { Calendar } from 'primereact/calendar';
         



function NumberModal(props)
{
  
    // const [value, onChange] = useState(new Date());
    const [totalprice,setTotalPrice] = useState(0);
    const [rank,setRank] = useState(0);
    const [eachPrice,setEachPrice] = useState([]);
    const [add,setAdd] = useState(0);
    const [DateMonth, setDateMonth] = useState(new Date());
    const [LuckyDate, setLuckyDate] = useState(new Date());
    const [nameDate,setNameDate] = useState('');
    // const [data,setdata] = useState();
    // const [row,setRow] = useState()
    const [userData, setUserData] = useState({dataSet:[{
     
        idList: add,
        number:'',
        price:"",
        allPrice:"",
        luckytime:new Date(),
        date: new Date(),
        option:"",
        sequence:""

    }]});
    const [mark,setmark] = useState();
    useEffect(()=>{
       // console.log("check props = ",props.show)
        if(props.show !== false)
        {
                 // console.log("check useEffect")
        // defaulData.push(object)
        // console.log("check data again = ",defaulData)
         console.log("check price = ",userData.dataSet[rank].number);
         let number = userData.dataSet[rank].number;
         let price = userData.dataSet[rank].price;
         //console.log("number check = ",number);
         //console.log("price check  = ",price);
         number = number.trim();
         number = number.replaceAll(","," ")
         number = number.split(" ");
         let arrNo = [];
         for(let i = 0 ; i<number.length ; i++)
         {
             if(number[i] !== ""){
                 if(number[i].length >1 && number[i].length<4)
                 {
                     if(Number(number[i]))
                     {
                         arrNo.push(number[i]);  
                     }
                    
                 }
             }
         }
         const allprice = arrNo.length * price 
         //console.log("each all price = ",eachPrice)
         eachPrice[rank] = allprice;
         //  setTotalPrice((totalprice+allprice))
        // console.log("each all price = ",eachPrice)
         const newData = { 
             ...userData,
             dataSet: [
               ...userData.dataSet.slice(0, rank), // copy everything before the updated item
               {
                 ...userData.dataSet[rank], // copy the item you want to update
                 ['allPrice']: allprice // update the property value using the computed property name
               },
               ...userData.dataSet.slice(rank + 1) // copy everything after the updated item
             ]
           };
           setUserData(newData);
           const sum = eachPrice.reduce((acc, curr) => acc + curr, 0);
           setTotalPrice(sum)
          }
          else{
           
            setTotalPrice(0)
            setEachPrice([])
          }

    },[userData.dataSet[rank].number,userData.dataSet[rank].price])
    
    if(props.show !== true)
    {
        return null;
    }

   
    //console.log("check each price = ",eachPrice)
    const handleChange = (...data) => {
       
        let value = data[0].target.value;
        let name =   data[0].target.name;
        const index = data[1];
        // let date_buy = new Date();
        // let luckytime =  new Date();
        const newData = UpdateData(name,value,index)
        setUserData(newData);
        setRank(index)
    }
    function handleDate(...data)
    {
        userData.dataSet[data[1]].date = data[0].target.value
    }
    function handleLuckyTime(...data)
    {   
        //console.log("check qq = ",data[1])

        userData.dataSet[data[1]].luckytime = data[0].target.value

    }
    function UpdateData(name,value,index)
    {
        const newData = { 
            ...userData,
            dataSet: [
              ...userData.dataSet.slice(0, index), // copy everything before the updated item
              {
                ...userData.dataSet[index], // copy the item you want to update
                [name]: value // update the property value using the computed property name
              },
              ...userData.dataSet.slice(index + 1) // copy everything after the updated item
            ]
          };
          return newData;
    }
    const Handlesaving = (isSave)=>{
       console.log("NUMBER modal = ",userData)
        props.handleSavingNum(isSave,userData,setUserData,mark,setRank,setTotalPrice)
     }
 
    function addRow()
    {
        const newData = {
            idList: add+1,
            date: new Date(),
            option:"",
            number:"",
            price:"",
            allPrice:"",
            luckytime:new Date(),
            sequence:""
    
        }
        setAdd(add+1)
        // setUserData([...userData.setData,newData])
        setUserData(prevState => ({
            ...prevState,
            dataSet: [...prevState.dataSet, newData]
          }));
       
        
    }
    // console.log("update = ",test)
       //console.log("new data = ",userData)
   
    return(
        <div className="boxmodal">
                <div className="modal-header" style={{"borderBottom":"solid gray"}} >
                    <div className="header-title" style={{"padding":"15px","color":"white"}}><h4>เพิ่มข้อมูล </h4></div>
                </div>
                <div className="boxbody"  style={{"marginLeft":"15px","marginRight":"20px"}}>
                    <div className="header-table" style={{"display":"flex"}}>
                        <div className="mark-no" style={{"color":"white"}} >
                            <label>ชุดที่</label>
                            <input type="number"  className="number-mark" style={{"width":"25%","textAlign":"center"}} onChange={(data)=>setmark(data.target.value)} />
                        </div>
                        <div className="plusImage"> <img src={plus} alt="plus" style = {{"width":"20px","height":"20px","cursor":"pointer"}} onClick={()=>addRow()}/> </div>
                    </div>
                    <div className="table-number" >
                        <table className="table table-bordered table-striped" style={{"color":"white"}}>
                            <thead>
                                <tr style={{"textAlign":"center"}}>
                                    <td style={{"width":"5%"}}>ลำดับ</td>
                                    <td style={{"width":"15%"}}>เลข</td>
                                    <td style={{"width":"10%"}}>ราคา</td>
                                    <td style={{"width":"12%"}}>การแทง</td>
                                    <td style={{"width":"20%"}}>งวดประจำวันที่</td>
                                    <td style={{"width":"20%"}}>วันที่ซื้อ</td>
                                    <td>ราคาทั้งหมด</td>
                                </tr>
                            </thead>
                            <tbody>
                                
                                {
                                    userData.dataSet.map((data,index)=>{
                                        return(
                                            <tr key = {index}>
                                            <td style={{"textAlign":"center"}}><span style={{"color":"white"}}>{index}</span></td>
                                            <td> <input type="text" className="form-control "  name = "number"  onChange={(e)=>handleChange(e,index)}/></td>
                                            <td> <input type="number" className="form-control price " name = "price"  onChange={(e)=>handleChange(e,index)}/></td>
                                            <td>
                                                <select className="form-select form-select-sm "  name = "option"    onChange={(e)=>handleChange(e,index)}>
                                                    <option value="empty">เลือก</option>
                                                    <option value="Top">บน</option>
                                                    <option value = "Below">ล่าง</option>
                                                    <option value = "Tod">โต๊ด</option>
                                                </select>
                                            </td>
                                            <td> <Calendar value={data.luckytime} onChange={(e) =>handleLuckyTime(e,index,'luckytime') } dateFormat="dd/mm/yy" style={{"height":"40px"}}/>  </td>
                                            <td><Calendar value={data.date} onChange={(e) => handleDate(e,index,'date')} dateFormat="dd/mm/yy"  style={{"height":"40px"}} /> </td>
                                            <td style={{"color":"#99FF99","fontSize":"24px","textAlign":"center"}}>{data.allPrice}</td>
                                        </tr>
                                     
                                        )
                                    })
                                }
                                <tr>
                                    <td></td>
                                    <td colSpan="5" style={{"textAlign":"right","color":"white"}}>ยอดรวมทั้งหมด</td>
                                    <td style={{"color":"#99FF99","fontSize":"24px","textAlign":"center"}} >{totalprice}</td>
                                </tr>

                            </tbody>
                        </table>
                    </div>  
                </div>
                <div className="modal-footer" style={{"marginTop":"15px","marginBottom":"15px","borderTop":"solid gray"}}>
                    <div className="footer">
                        <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" onClick={() => Handlesaving(false)}>Close</button>
                        <button type="button" className="btn btn-primary" style={{"marginLeft":"10px"}} name = "save" onClick={(e)=>Handlesaving(true)}>Save changes</button>
                    </div>
                </div>
        </div>

    );

}
export default NumberModal;