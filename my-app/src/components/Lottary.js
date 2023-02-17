import React, { useEffect, useState } from "react";
import "../CSS/Lottary.css"
import NumberModal from "./Modal/NumberModal"
import * as urlConstant from "../components/Constant/UrlConstant"
import InsertNumberModal from "./Modal/InsertNumberModal";
import PaymentStatusModal from "../components/Modal/PaymentStatusModal"
import InfoUserModal from "./Modal/infoUserModal"
import DatePicker from "react-datepicker";
import { useNavigate } from "react-router-dom";
import axios from 'axios';



function Paginagtion({totalPosts,postsPerPage,paginate})
{
   

    const pageNumber =[];
    for(let i = 1; i<=Math.ceil(totalPosts / postsPerPage); i++)
    {
        pageNumber.push(i);
    }
    console.log("pageNumber = "+pageNumber)
    return(
        <nav aria-label="..." style={{"marginLeft":"120px","marginTop":"20px","position":"absolute","zIndex":"-1"}}>
            <ul className="pagination" >
                <li className="page-item disabled">
                <a className="page-link">Previous</a>
                </li>
                {pageNumber.map((number) =>{
                     return(
                        <li className="page-item" key = {number}><a onClick={()=>paginate(number)}className="page-link" href="#">{number}</a></li>
                     )
                })}
              
                <li className="page-item"> <a className="page-link" href="#">Next</a> </li>
            </ul>
        </nav>
        
    )
}
function Lottary()
{
    //** pagination  */
    const [currentPage,setCurrentPage] = useState(1);
    const [postsPerPage] = useState(10);

    const [popup,setpopup] =  useState(false);
    const [status,setStatus] = useState();
    const [luckyModal,setLuckyModal]  =useState(false);
    const [isOpenPaymentModal,setIsOpenPayMentModal] = useState(false);
    const [isOpenInfoUserModal,setInfoUserModal] = useState(false);
    const [DateMonth, setDateMonth] = useState(new Date());
    const [dataSet,setDataSet] = useState([{idlist:"",number:"",price:"",optionpurchase:"",status:"",datebuy:"",time:"",statuspayment:"",luckytime:"",id:""}]);
    const [newItem,setNewItem] = useState([{idlist:"",number:"",price:"",optionpurchase:"",status:"",datebuy:"",time:"",statuspayment:"",luckytime:"",id:""}]);
    const [sessionUser,setSession] = useState(sessionStorage.getItem("token"));
    const [DataLuckyNumber,setDataLuckyNumber] = useState();
    const [select ,setSelect] = useState();
    const [ newData,setNewData] = useState({
        id: "",
        date: "", 
    });
    // let navigate = useNavigate()
   //console.log("check data = ",dataSet)
    let session =  sessionStorage.getItem("token");
    axios.interceptors.request.use(
        config =>{
          config.headers.Authorization = `Bearer ${session}`;
          return config;
        }
      )

      axios.interceptors.response.use(undefined,(error) =>{
        const {status,data,config} = error.response;
        if(status === 404)
        {
          window.location.assign("pagenotfound")
        }
        if(status === 400)
        {
          console.log("bad request")
        }
        if(status ===500)
        {
          console.log("error server");
          alert("ERROR 500")
        }
        if(status === 401)
        {
             window.location.assign("/login")
        }
        if(status ===403)
        {
          window.location.assign("/login")
        }
      })
    if(session === null || session === undefined || session ==="")
    {
      window.location.assign("/login")
    }
    useEffect(()=>{
                  async function getListitem()
                  {
                    try{
                        const response = await axios.get(urlConstant.GET_LIST_LOTTARY,{
                            headers: { 'Content-Type': 'application/json' }
                        })
                        if(response.data != undefined)
                        {
                            setDataSet(response.data.datalist)
                            setNewItem(response.data.datalist)
                           
                        }

                        console.log("check response data = ",response );
                    }catch(error)
                    {
                        console.error(error);
                    }
                  }
                getListitem();

    },[])
    // console.log("check 1 = ",popup.show)
    function ValidityState()
    {
        setpopup(true);
       
    }
    function setData(e)
    {
        setNewData(e);
    }
    function submitData(e)
    {
        if(e === "save")
        {
            console.log("newdata = ",newData.date)
            if(newData.date === undefined || newData.date === "")
            {
                alert("กรุุณากรอก Date");
            }
            else if (newData.option === undefined || newData.option === "")
            {
                alert("กรุุณากรอก Option");
            }
            else if (newData.number === undefined || newData.number === "")
            {
                alert("กรุุณากรอก number");
            }
            else if (newData.price === undefined || newData.price === "")
            {
                alert("กรุุณากรอก price");
            }
            else{
                const response = axios.post(urlConstant.SAVE_NUMBER_BUYING,newData, {
                    headers: { 'Content-Type': 'application/json' }
                })
            }
         console.log("check e ",newData)
        }
    }
    // console.log(popup)
    //todo validate the  saving status from InsertLuckyNumber model
    function HandleInsertNumber(e,DataLuckyNumber)
    {
        console.log("save InsertNUmber = ",e,DataLuckyNumber);
        if(e === "save")
        {  // todo validate field  of Insert LuckyNumber Model
            if(DataLuckyNumber.threetop === null || DataLuckyNumber.threetop === undefined || DataLuckyNumber.threetop === "" )
            {
                alert("กรุณาใส่เลข เลขหน้า 3 ตัว");
            }
            else if(DataLuckyNumber.threedown === null || DataLuckyNumber.threedown === undefined || DataLuckyNumber.threedown === "" )
            {
                alert("กรุณาใส่เลข เลขหท้าย 3 ตัว");
            }
            else if(DataLuckyNumber.twotop === null || DataLuckyNumber.twotop === undefined || DataLuckyNumber.twotop === "" )
            {
                alert("กรุณาใส่เลขเลขท้าย 2 ตัว");
            }
            else if(DataLuckyNumber.twodown=== null || DataLuckyNumber.twodown === undefined || DataLuckyNumber.twodown === "" )
            {
                alert("กรุณาใส่เลข เลขท้าย 2 ตัว");
            }
            else{
                const post_Insert_Lucky_Number =axios.post(urlConstant.POST_INSERT_LUCKY_NUMBER,DataLuckyNumber,{
                    headers: { 'Content-Type': 'application/json' }
                }).then(res=>{
                  
                    //  console.log("code = ",res)
                    let message = res.data.message;
                    let statusCode = res.data.statusCode;
                    // todo validate status insert from back end 
                    if(statusCode ==='01' && message === "success")
                    {
                        alert("ทำรายการสำเร็จ")
                        setLuckyModal(false)
                         window.location.reload()
                    }
                    else if(statusCode ==='01' && message === 'duplicate_data')
                    {
                        alert("มีการทำรายการซ้ำของวันที่")
                    }
                    else{
                        alert("เกิดข้อผิดพลาดในการทำรายการ")
                    }
                })
            
          //  console.log("check post insert  = ",post_Insert_Lucky_Number)

            }
        }
        //* close InsertLucky Number Model */
        else{
            setLuckyModal(false)
        }
       // console.log("show lucky number ",DataLuckyNumber)
    }
    function HandleNumberModal(isOpen,dataNum)
    {
     
        if(isOpen)
        {
            console.log(dataNum.option);
            if(dataNum.option === null || dataNum.option ===undefined || dataNum.option ==='')
            {
                alert("กรุณาเลือกการแทง");
            }
            else if(dataNum.price  === null || dataNum.price ===undefined || dataNum.price ==='' )
            {
                alert("กรุณาใส่ราคา");
            }
            else if(dataNum.number  === null || dataNum.number ===undefined || dataNum.number ==='' )
            {
                alert("กรุณาใส่เลข");
            }
            else{
                const Post_Insert_Number = axios.post(urlConstant.POST_INSERT_NUMBER,dataNum,{
                    headers: { 'Content-Type': 'application/json' }
                }).then(res =>{
                    console.log("check response num = ",res.data)
                    if(res.data.message === 'not_success' && res.data.statusCode === '01')
                    {
                        alert("ทำรายการไม่สำเร็จ");
                    }
                    else{
                        alert("ทำรายการสำเร็จ");
                        setpopup(false);
                        window.location.reload(false)
                    }
                })

            }
        }
        else{
            setpopup(false)
        }
    }

    //TODO: filter data after click search buttom 
    function SearchData()
    {
        console.log("check select = ",select)
        if(select ==="Yes")
        {
            //** use object.values because the output is [Object,Object] */
            setDataSet(newItem.filter(data=> data.statuspayment ==="Yes"))
            console.log("check search = ",currentPosts)
          
        }
        else if(select === "No"){
               //** use object.values because the output is [Object,Object] */
            setDataSet(newItem.filter(data=> data.statuspayment ==="No"))
            console.log("check search = ",currentPosts)
        }
        else{
            setDataSet(newItem)
        }
    }


    // ** get current post

    const indexOfLastPost = currentPage * postsPerPage;
    const indexOfFirstPost = indexOfLastPost - postsPerPage;
    let currentPosts = dataSet.slice(indexOfFirstPost,indexOfLastPost);
    const paginate = pageNumber =>setCurrentPage(pageNumber)

    return(
        <div className="mainpage">
            <div className="boxpage" >
                    <div className="title" style={{"textAlign":"center","marginTop":"20px",}}>
                       <h3>รายการหวย ประจำวันที่ 16/10/2565</h3>
                    </div>
                    <div className="info" style={{"marginTop":"40px","display":"flex","flexDirection":"column"}}> 
                        <div className="setBtn"style={{"display":"flex","margin":"0 auto"}}>
                            <div className=" Action-btn">
                                <button type="button" className="btn btn-light"  onClick={()=>ValidityState()}>เพิ่มข้อมูล</button>
                            </div>
                            <div className=" Action-btn">
                                <button type="button" className="btn btn-light"  onClick={()=>setLuckyModal(true)}>เลขถูก</button>
                            </div>
                            <div className=" Action-btn">
                                <button type="button" className="btn btn-primary" onClick={()=>SearchData()}>ค้นหา</button> 
                             </div>
                            <div className=" Action-btn">
                                <select className="selectOption" style={{"marginTop":"5px"}} onChange={(e)=>setSelect(e.target.value)}>
                                    <option value = "All">ทั้งหมด</option>
                                    <option value = "No">ยังไม่จ่าย</option>
                                    <option value = "Yes">จ่ายแล้ว</option>
                                </select>
                            </div>
                        </div>
                        <div className="setText" style={{"display":"flex","margin":"0 auto","marginTop":"10px"}}>
                            <div className="constantText"><label style={{"fontSize":"24px","marginLeft":"20px"}}>เงินต้น : 20000</label></div>
                            <div className="constantText"><label style={{"fontSize":"24px","marginLeft":"20px"}}>ยอดเงินคนถูก : 20000</label></div>
                            <div className="constantText"><label style={{"fontSize":"24px","marginLeft":"20px"}}>เลขหน้า 3 ตัว : 111 </label></div>
                            <div className="constantText"><label style={{"fontSize":"24px","marginLeft":"20px"}}>เลขท้าย 3 ตัว : 222</label></div>
                            <div className="constantText"><label style={{"fontSize":"24px","marginLeft":"20px"}}>เลขท้าย 2 ตัว : 33</label></div>
                        </div>     
                    </div>

                    <div className="listdatanumber" style={{"marginBottom":"15px"}}>
                        <table style={{"border":"solid 2px yellow","width":"80%","margin":"0 auto","marginTop":"50px"}}  className="table table-striped">
                           <thead >
                            <tr style={{"border":"solid 2px white"}}>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>ลำดับ</td>
                                <td style={{"width":"10%","border":"solid 2px yellow","textAlign":"center"}}>เลข</td>
                                <td style={{"width":"10%","border":"solid 2px yellow","textAlign":"center"}}>ราคา (บาท)</td>
                                <td style={{"width":"10%","border":"solid 2px yellow","textAlign":"center"}}>การแทง</td>
                                <td style={{"width":"10%","border":"solid 2px yellow","textAlign":"center"}}>สถานะ</td>
                                <td style={{"width":"10%","border":"solid 2px yellow","textAlign":"center"}}>วันที่</td>
                                <td style={{"width":"15%","border":"solid 2px yellow","textAlign":"center"}}>เวลา</td>
                                <td style={{"width":"10%","border":"solid 2px yellow","textAlign":"center"}}>การจ่าย</td>
                                <td style={{"width":"30%","border":"solid 2px yellow","textAlign":"center"}}>การจัดการ</td>
                            </tr>
                           </thead>
                           <tbody>
                            {
                                currentPosts.map((resp,index)=>{
                                    console.log(resp.number);
                                   return(
                                     <tr key = {index}>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span>{index+1}</span></td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span>{resp.number}</span></td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span>{resp.price}</span></td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span>{resp.optinpurchase}</span></td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span className={resp.status ==="Lucky" ? "Lucky":"unLucky"}>{resp.status}</span></td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span>{resp.datebuy}</span></td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span>{resp.time}</span></td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span className={resp.statuspayment === 'Yes' ? 'paynow':'notpay'}>{resp.statuspayment}</span></td>
                                <td  style={{"border":"solid 2px yellow"}}>
                                    <div className="allbuttom">
                                        <div  className="buttom1">
                                        <button type="button" className="btn btn-info"  onClick={()=>setIsOpenPayMentModal(true)}>แก้ไข</button>
                                        </div>
                                   
                                    <div className="buttom2"> <button type="button" className="btn btn-light"  onClick={()=>setInfoUserModal(true)}>ข้อมูล</button></div>
                                    </div>                    
                                </td>
                            </tr>
                                   )
                                  
                                })
                            }
                          
                           </tbody>
                        </table> 
                        <Paginagtion   totalPosts={dataSet.length} postsPerPage = {postsPerPage} paginate={paginate} />
                </div>
       
                   
            </div>
            {/* // list insert purachse number  modal */}
            <NumberModal  handleSavingNum={(isOpen,dataNum) => HandleNumberModal(isOpen,dataNum)}  show={popup}   />

            {
            // todo  insert modal lottary
                luckyModal && <InsertNumberModal  handleSaving = {(status_saving,DataLuckyNumber)=>HandleInsertNumber(status_saving,DataLuckyNumber)} luckyNumber ={(e)=>setDataLuckyNumber(e)}/>
            }
            {
                // todo paymethod modal changing
            isOpenPaymentModal &&   <PaymentStatusModal onClose={(e) => setIsOpenPayMentModal(e)} />
            }
            {
                
            isOpenInfoUserModal &&   <InfoUserModal onClose={(e) => setInfoUserModal(e)}/>
            }

        </div>
    );
}

export default Lottary;