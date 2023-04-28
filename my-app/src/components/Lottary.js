import React, { useEffect, useState } from "react";
import "../CSS/Lottary.css"
import NumberModal from "./Modal/NumberModal"
import * as urlConstant from "../components/Constant/UrlConstant"
import InsertLuckyNumberModal from "./Modal/InsertLuckyNumberModal";
import PaymentStatusModal from "../components/Modal/PaymentStatusModal"
import DeleteModal from "./Modal/DeleteDataModal"
import DatePicker from "react-datepicker";
import {  useNavigate } from "react-router-dom";
import SendLottaryModal from "./Modal/SendLottaryModal";
import axios from 'axios';
import { Route, Link, BrowserRouter,Routes } from 'react-router-dom'  

function Lottary()
{
    let navigate = useNavigate();
    //** pagination  */
    const [currentPage,setCurrentPage] = useState(1);
    const [postsPerPage] = useState(10);
    const [pageNumberLimit, setpageNumberLimit] = useState(5);
    const [maxPageNumberLimit, setmaxPageNumberLimit] = useState(5);
    const [minPageNumberLimit, setminPageNumberLimit] = useState(0);
    const [add,setAdd] = useState(0);
    const [popup,setpopup] =  useState(false);
    const [status,setStatus] = useState();
    const [luckyModal,setLuckyModal]  =useState(false);
    const [isOpenPaymentModal,setIsOpenPayMentModal] = useState(false);
    const [isOpenDeleteModal,setIsOpenDeleteModal] = useState(false);
    const [isOpenSendLottaryModa,setIsOpenSendLottaryModal] = useState(false);
    const [DateBuy, setDateBuy] = useState("");
    const [DateSelect,setDataSelect] = useState(new Date());
    const [IsSelect,setIsSelect] = useState(false);
    const [dateLucky,setDateLucky] = useState("");
    const [dataSet,setDataSet] = useState([{idlist:"",number:"",price:"",all_price:"",optionpurchase:"",status:"",datebuy:"",time:"",statuspayment:"",luckytime:"",id:""}]);
    const [newItem,setNewItem] = useState([{idlist:"",number:"",price:"",all_price:"",optionpurchase:"",status:"",datebuy:"",time:"",statuspayment:"",luckytime:"",id:""}]);
    const [sessionUser,setSession] = useState(sessionStorage.getItem("token"));
    const [DataLuckyNumber,setDataLuckyNumber] = useState();
    const [index,setIndex] = useState();
    const [select ,setSelect] = useState();
    const [statusLucky,setStatusLucky] = useState("All");
    const [collectNumber,setCollectNumber] = useState({date:"",threetop:"",threedown:"",twotop:"",twodown:""});
    const [ newData,setNewData] = useState({
        id: "",
        date: "", 
    });
    const [DataDelete,setDataDelete] = useState({id:"",idlist:""})
    // let navigate = useNavigate()
   //console.log("check data = ",dataSet)
    let session =  sessionStorage.getItem("token");
    // console.log("test data = ",collectNumber);
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
            alert("BAD Request 400")
        }
        if(status ===500)
        {
          console.log("error server");
           
           window.location.assign("/internalserver")
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
                    let date = null;
                    if(IsSelect)
                    {
                            // date = (DateSelect.getFullYear()+"-"+(1+Number(DateSelect.getMonth()))+"-"+DateSelect.getDate());
                    }
                    else{
                        date = "lastdata";
                    }
                        try{
                            const response = await axios.get(urlConstant.GET_LIST_LOTTARY+date,{
                                headers: { 'Content-Type': 'application/json' }
                                
                            })
                            // console.log("check response.data  = ",response );
                            // console.log("check response.data  = ",response.data );
                            if(response.data != undefined && response.data.datalist != null)
                            {
                                setDataSet(response.data.datalist)
                                // setDateLucky(response.data.datalist.luckytime)
                                setNewItem(response.data.datalist)   
                            }
                           // console.log("check response data = ",response );
                        }catch(error)
                        {
                            console.error(error);
                        }
                  }
                getListitem();

    },[])
    // console.log("check 1 = ",popup.show)
 
    function Paginagtion({totalPosts,postsPerPage,paginate})
{
    const pageNumber =[];
    for(let i = 1; i<=Math.ceil(totalPosts / postsPerPage); i++)
    {
        pageNumber.push(i);
    }
    // console.log("pageNumber = "+pageNumber)
    // console.log("qqq",paginate)
    return(
        <nav  className="Nopagination" style={{"marginLeft":"120px","marginTop":"30px","position":"absolute","zIndex":"-1"}}>
            <ul className="pagination" >
                <li className="page-item disabled">
                <a className="page-link">Previous</a>
                </li>
                {pageNumber.map((number) =>{
                     return(
                        <button className={number == currentPage ?'active':'inactive'} key = {number}  onClick={()=>paginate(number)}>{number}</button>
                     )
                })}
              
                <li className="page-item">  <button > Next </button> </li>
            </ul>
        </nav>
    )
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
    function DatePickert(date)
    {
           // console.log("check date = e  = ",date)
        //    console.log("check month = ",date)
            setDateBuy(date)
            setIsSelect(true)
            setDataSelect(date)
    }
    // TODO : delete data from table 
    function DeleteData(status,value)
    {
        console.log("check status delete = ",DataDelete);
        if(status ==='Yes')
        {
            try{
                const response =  axios.post(urlConstant.POST_DELETEDATAT,DataDelete,{
                    headers: { 'Content-Type': 'application/json' }
                }).then((resp)=>{
                    console.log(resp)
                    if(resp.status=== 200)
                    {
                        alert("ทำรายการสำเร็จ");
                        setIsOpenDeleteModal(false)
                        let timeout;
                        function myFunction() {
                            timeout = setTimeout(()=>{window.location.reload(false)}, 1000);
                          }
                        myFunction()
                    }
                })

            }catch(error)
            {
                console.error(error)
            }
        }
        else{
            setIsOpenDeleteModal(false)
        }
    }
    //todo validate the  saving status from InsertLuckyNumber model
    function HandleInsertLuckyNumber(e,DataLuckyNumber)
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
                        // setCollectNumber(res.data.datalist)
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
    function HandlePayment(status_saving,DataPayment)
    {
        console.log("check index3 = ",newItem[0])
        if(status_saving === 'save')
        {
            let indexUpdate = 0;
            let dataUpdate = {};
             newItem.map((item,no)=>{
                if(item.idlist === index ){
                   
                    item.statuspayment = DataPayment.status;
                    dataUpdate  = item
                }
            })
            try{
                const response  = axios.post(urlConstant.POST_UPDATE__STATUS_PAYMENT,dataUpdate,{
                    headers: { 'Content-Type': 'application/json' }
                }).then(res =>{
                    if(res.data.message === 'success' && res.data.statusCode ==='01')
                {
                    alert("ทำรายการสำเร็จ")
                    setIsOpenPayMentModal(false)      
                }

                    window.location.reload()
                })

            }catch(err)
            {
                console.error("Error = ",err);
            }
        }
        else{
            console.log("check index2 = ",newItem)
            setIsOpenPayMentModal(false)      
        }
        // console.log(newItem)
        //console.log("check paymnet = ",status_saving,DataPayment)
    }
    async function HandleNumberModal(isSave,dataNum,setUserData,mark,setRank,setTotalPrice)
    {
        console.log("handle number modal = ",dataNum)
        console.log("handle number modal = ",dataNum.dataSet[0])
        console.log("mark = ",mark)
      
      
        let statusValidate = true;
        if(isSave)
        {
            // console.log("data price = ",dataNum.price[0]);
            
            if(mark !== '' && mark !== null && mark !== undefined)
            {
                if(mark >0)
                {
                    for(let i = 0; i<dataNum.dataSet.length; i++)
                    {
                        let arrNo = [];
                        if(dataNum.dataSet[i].option === null ||  dataNum.dataSet[i].option ===undefined || dataNum.dataSet[i].option ==='' || dataNum.dataSet[i].option ==='empty')
                        {
                            alert("กรุณาเลือกการแทง");
                            statusValidate = false;
                            break
                            
                        }
                        else if(dataNum.dataSet[i].price  === null || dataNum.dataSet[i].price ===undefined || dataNum.dataSet[i].price ===''||  Number(dataNum.dataSet[i].price[0])=== 0 )
                        {
                            alert("กรุณาใส่ราคา");
                            statusValidate = false;
                            break
                            
                        }
                        else if(dataNum.dataSet[i].number  === null || dataNum.dataSet[i].number ===undefined || dataNum.dataSet[i].number ==='' )
                        {
                            alert("กรุณาใส่เลข");
                            statusValidate = false;
                            break
                            
                        }
                        else{
                            let number = dataNum.dataSet[i].number;
                            number = number.trim();
                            number = number.replaceAll(","," ")
                            number = number.split(" ");
                            console.log("check number = ",number);
                            if(dataNum.option === 'Tod')
                            {
                                for(let i = 0 ; i<number.length ; i++)
                                {
                                    if(Number(number[i])){
                                        if(number[i].length === 3)
                                        {
                                            if(Number(number[i]))
                                            {
                                                arrNo.push(number[i]);  
                                            }
                                            else{
                                                alert("ห้ามตัวอักษร");
                                                statusValidate = false;
                                                break;
                                            }
                                        }
                                        else{
                                            alert("มีบางเลขไม่ครบ3หลัก")
                                            console.log("check qq = ",number[i])
                                            statusValidate = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            else{
                                for(let i = 0 ; i<number.length ; i++)
                                {
                                    if(Number(number[i])){
                                        if(number[i].length >1 && number[i].length<4)
                                        {
                                            if(Number(number[i]))
                                            {
                                                arrNo.push(number[i]);  
                                            }
                                            else{
                                                alert("ห้ามตัวอักษร");
                                                statusValidate = false;
                                                break;
                                            }
                                        }
                                        else{
                                            if(Number(number[i]))
                                            {
                                                alert("มีบางเลขน้อยกว่า1หลักแต่ต้องไม่เกิน3หลัก")
                                                statusValidate = false;
                                            }
                                            else{
                                                alert("ห้ามตัวอักษร");
                                                statusValidate = false;
                                                break;
                                            }
                                            statusValidate = false;
                                            break;
                                        }
                                    }
                                  
                                }
                                dataNum.dataSet[i].number = arrNo.join(",");
                            }
                        }
                    }
                }
                else{
                    alert("เลขชุดต้องมากกว่า 0")
                    statusValidate = false;
                }
            }
            else{
                alert("กรุณาใส่เลขชุดที่")
                statusValidate = false;
            }
           
            // console.log("check num = ",dataNum)
            if(statusValidate)
            {
                // dataNum.number = arrNo.join(",");
                const answer = window.confirm("ต้องการบันทึกข้อมูลไหม ?");
                console.log("check last data = ",dataNum)
                if(answer)
                {
                    dataNum.dataSet.map((data,index)=>{
                        let date = data.date;
                        let lucky_date = data.luckytime;
                        date = date.getFullYear()+"-"+(1+Number(date.getMonth()))+"-"+date.getDate();
                        lucky_date =  lucky_date.getFullYear()+"-"+(1+Number(lucky_date.getMonth()))+"-"+lucky_date.getDate();
                       // console.log("date = ",date);
                        data.date = date;
                        data.luckytime = lucky_date;
                            
                    })
                    try{
                    
                        const Post_Insert_Number = await axios.post(urlConstant.POST_INSERT_NUMBER,dataNum,{
                            headers: { 'Content-Type': 'application/json' }
                        }).then(res =>{
                                // console.log("check response num = ",res.data)
                        if(res.data.message === 'not_success' && res.data.statusCode === '01')
                        {
                            alert("ทำรายการไม่สำเร็จกรุณาตรวจสอบข้อมูล");
                        }
                        else{
                            alert("ทำรายการสำเร็จ");
                            setpopup(false);
                            reload()
                                    // window.location.reload(false)
                            }
                        })
                        }catch(error)
                        {
                            console.error(error)
                        }
                    }
                 
                
            }
        }
        else{
            setpopup(false)
            setRank(0)
            setUserData({dataSet:[{
     
                id: add,
                date: new Date(),
                option:"",
                number:"",
                price:"",
                all_price:"",
                idLine:"",
                phoneNumber:"",
                luckytime:new Date(),
                set:""
        
            }]})
        }
    }
    function SetPaymentMethod(index)
    {
        setIsOpenPayMentModal(true)
        setIndex(index)
        //console.log("check index = "+index)
    }
    function SetDeleteModal(status,idlist,id)
    {
        setIsOpenDeleteModal(status);
         console.log("check id list = ",idlist,id)
        setDataDelete({...DataDelete,idlist:idlist,id:id})
    }
    async function HandleSendLottaryModal(status,date)
    {
        console.log("check status sending = ",status,date)

        if(status ==="Yes")
        {
            const response = await axios.post(urlConstant.POST_SEND_LOTTARY,date,{
                headers: { 'Content-Type': 'application/json' }
            })
            console.log("check response sendding  ",response);
            if(response.data.message ==="empty")
            {
                alert("ไม่มีข้อมูลให้ทำรายการ")
            }
            else if(response.data.message ==="duplicate_data")
            {
                alert("ไม่สามารถทำรายการซ้ำได้");
            }
            else if(response.data.message ==="success")
            {
                alert("ทำรายการสำเร็จ");
                reload()
            }

        }
        else{
            setIsOpenSendLottaryModal(false);
        }
    }
    function reload()
    {
        let timeout;
        timeout = setTimeout(()=>{window.location.reload(false)}, 1000);
    }
    //TODO: filter data after click search buttom 
    function SearchData()
    {
        let dateBuy = null;
        let dateTimeLucky = null;
        
        // console.log("check = asdasd",newItem)
        if(DateBuy !== null && DateBuy !== '' && DateBuy !== undefined)
        {         
            dateBuy = (DateBuy.getFullYear()+"-"+String(1+Number(DateBuy.getMonth())).padStart(2, '0')+"-"+String(DateBuy.getDate()).padStart(2, '0'));
        }
        if(dateLucky !== null && dateLucky !== '' && dateLucky !== undefined)
        {
            dateTimeLucky = (dateLucky.getFullYear()+"-"+String(1+Number(dateLucky.getMonth())).padStart(2, '0')+"-"+String(dateLucky.getDate()).padStart(2, '0'));
        }
        if(select ==="Yes")
        {
            console.log("date buy = ",dateTimeLucky)
            //** use object.values because the output is [Object,Object] */
            if(dateBuy !== null && dateBuy !== undefined && dateBuy !== '' && dateLucky !== null && dateLucky !=='' && dateLucky !== undefined )
            {
               // console.log("checko yes 1= ",newItem);
                if(currentPage>1)
                {
                    setCurrentPage(1)
                    if(statusLucky !=='All')
                    {
                         console.log("check ถูกรางงวัลไม่จ่าย")
                         setDataSet(newItem.filter(data=> data.statuspayment === select && data.datebuy === dateBuy && data.luckytime === dateTimeLucky && data.status === statusLucky))
                    }
                    else{
                     setDataSet(newItem.filter(data=> data.statuspayment ===select && data.datebuy === dateBuy && data.luckytime === dateTimeLucky))
                    }
                }else{
                    if(statusLucky !=='All')
                    {
                         console.log("check ถูกรางงวัลไม่จ่าย")
                         setDataSet(newItem.filter(data=> data.statuspayment === select && data.datebuy === dateBuy && data.luckytime === dateTimeLucky && data.status === statusLucky))
                    }
                    else{
                     setDataSet(newItem.filter(data=> data.statuspayment ===select && data.datebuy === dateBuy && data.luckytime === dateTimeLucky))
                    }
                }
            }
            else if(dateBuy !== null && dateBuy !== undefined && dateBuy !== '')
            {
               // console.log("checko yes 2 = ",newItem);
                if(currentPage>1)
                {
                    setCurrentPage(1)
                    if(statusLucky !=='All')
                    {
                         console.log("check ถูกรางงวัลไม่จ่าย")
                         setDataSet(newItem.filter(data=> data.statuspayment === select &&  data.datebuy === dateBuy && data.status === statusLucky))
                    }
                    else{
                         setDataSet(newItem.filter(data=> data.statuspayment ==="Yes" && data.datebuy === dateBuy))
                    }
                }
                else{
                    if(statusLucky !=='All')
                    {
                         console.log("check ถูกรางงวัลไม่จ่าย")
                         setDataSet(newItem.filter(data=> data.statuspayment === select &&  data.datebuy === dateBuy && data.status === statusLucky))
                    }
                    else{
                         setDataSet(newItem.filter(data=> data.statuspayment ==="Yes" && data.datebuy === dateBuy))
                    }
                }
            }
            else if(dateLucky !== null && dateLucky !=='' && dateLucky !== undefined)
            {
                if(currentPage>1)
                {
                    setCurrentPage(1)
                    if(statusLucky !=='All')
                    {
                        console.log("check ถูกรางงวัลไม่จ่าย")
                        setDataSet(newItem.filter(data=> data.statuspayment === select &&  data.luckytime === dateTimeLucky && data.status === statusLucky))
                    }
                    else{
                        setDataSet(newItem.filter(data=> data.statuspayment ===select && data.luckytime === dateTimeLucky ))
                    }
                }
                else{
                    if(statusLucky !=='All')
                    {
                        console.log("check ถูกรางงวัลไม่จ่าย")
                        setDataSet(newItem.filter(data=> data.statuspayment === select &&  data.luckytime === dateTimeLucky && data.status === statusLucky))
                    }
                    else{
                        setDataSet(newItem.filter(data=> data.statuspayment ===select && data.luckytime === dateTimeLucky ))
                    }
                }
            }
            else if(statusLucky !== null && statusLucky !=='' && statusLucky !== undefined && statusLucky !='All')
            {
             // console.log("check ถูกรางวัลแต่ไม่จ่าย  = ",select,statusLucky)
                if(currentPage>1)
                {
                    setCurrentPage(1)
                    setDataSet(newItem.filter(data=> data.status === statusLucky && data.statuspayment === select ))
                }
                else{
                    setDataSet(newItem.filter(data=> data.status === statusLucky && data.statuspayment === select ))
                }
            }
            else{
              //  console.log("checko yes 3 = ",newItem);
                if(currentPage>1)
                {
                  setCurrentPage(1)
                  setDataSet(newItem.filter(data=> data.statuspayment ===select ))
                }
                else{
                  setDataSet(newItem.filter(data=> data.statuspayment ===select ))
                }
            }
           
            console.log("check search = ",currentPosts)   
        }
        else if(select === "No"){
               //** use object.values because the output is [Object,Object] */
               if(dateBuy !== null && dateBuy !== undefined && dateBuy !== '' && dateLucky !== null && dateLucky !=='' && dateLucky !== undefined )
                {
                    //console.log("checko no 1= ",newItem);
                    if(currentPage>1)
                    {
                        setCurrentPage(1)
                        if(statusLucky !=='All')
                        {
                            console.log("check ถูกรางงวัลไม่จ่าย")
                            setDataSet(newItem.filter(data=> data.statuspayment ===select && data.datebuy === dateBuy && data.luckytime === dateTimeLucky && data.status === statusLucky ))
                        }
                        else{
                            setDataSet(newItem.filter(data=> data.statuspayment ===select && data.datebuy === dateBuy && data.luckytime === dateTimeLucky))
                        }
                    }
                    else{
                        if(statusLucky !=='All')
                        {
                            console.log("check ถูกรางงวัลไม่จ่าย")
                            setDataSet(newItem.filter(data=> data.statuspayment ===select && data.datebuy === dateBuy && data.luckytime === dateTimeLucky && data.status === statusLucky ))
                        }
                        else{
                            setDataSet(newItem.filter(data=> data.statuspayment ===select && data.datebuy === dateBuy && data.luckytime === dateTimeLucky))
                        }
                    }
                  
                }
               else if(dateBuy !== null && dateBuy !== undefined && dateBuy !== '')
               {
                    if(currentPage>1)
                    {
                        currentPage(1)
                        if(statusLucky !=='All')
                        {
                            console.log("check ถูกรางงวัลไม่จ่าย")
                            setDataSet(newItem.filter(data=> data.statuspayment === select && data.datebuy === dateBuy && data.status === statusLucky ))
                        }
                        else{
                            setDataSet(newItem.filter(data=> data.statuspayment === select && data.datebuy === dateBuy))
                        }
                    }
                    else{
                        if(statusLucky !=='All')
                        {
                            console.log("check ถูกรางงวัลไม่จ่าย")
                            setDataSet(newItem.filter(data=> data.statuspayment === select && data.datebuy === dateBuy && data.status === statusLucky ))
                        }
                        else{
                            setDataSet(newItem.filter(data=> data.statuspayment === select && data.datebuy === dateBuy))
                        }
                    }
                  // console.log("checko new item = ",newItem);
               }
               else if(dateLucky !== null && dateLucky !=='' && dateLucky !== undefined)
               {
                    if(currentPage>1)
                    {
                        setCurrentPage(1)
                        if(statusLucky !=='All')
                        {
                            console.log("check ถูกรางงวัลไม่จ่าย")
                            setDataSet(newItem.filter(data=> data.statuspayment === select &&  data.luckytime === dateTimeLucky && data.status === statusLucky))
                        }
                        else{
                            setDataSet(newItem.filter(data=> data.statuspayment === select && data.luckytime === dateTimeLucky ))
                        }
                    }
                    else{
                        if(statusLucky !=='All')
                        {
                            console.log("check ถูกรางงวัลไม่จ่าย")
                            setDataSet(newItem.filter(data=> data.statuspayment === select &&  data.luckytime === dateTimeLucky && data.status === statusLucky))
                        }
                        else{
                            setDataSet(newItem.filter(data=> data.statuspayment === select && data.luckytime === dateTimeLucky ))
                        }
                    }
               // console.log("checko new 3 = ",newItem);
                // setDataSet(newItem.filter(data=> data.statuspayment ==="No" && data.luckytime === dateTimeLucky ))
               }
               else if(statusLucky !== null && statusLucky !=='' && statusLucky !== undefined && statusLucky !=='All')
               {
                // console.log("check ถูกรางวัลแต่ไม่จ่าย  = ",select,statusLucky)
                   if(currentPage>1)
                    {
                        setCurrentPage(1)
                        setDataSet(newItem.filter(data=> data.status === statusLucky && data.statuspayment === select ))
                    }
                    else{
                        setDataSet(newItem.filter(data=> data.status === statusLucky && data.statuspayment === select ))
                    }
               }
               else{
                  // console.log("checko new item = ",newItem);
                  if(currentPage>1)
                  {
                    setCurrentPage(1)
                    setDataSet(newItem.filter(data=> data.statuspayment ===select ))
                  }
                  else{
                    setDataSet(newItem.filter(data=> data.statuspayment ===select ))
                  }
                  
               }
           // console.log("check search No = ",currentPosts)
        }
        else{
            // console.log("check status ",statusLucky)
            if(dateBuy !== null && dateBuy !== undefined && dateBuy !== '' && dateLucky !== null && dateLucky !=='' && dateLucky !== undefined  )
            {
                console.log("checko no 1= ",newItem);
                if(currentPage>1)
                {
                    setCurrentPage(1)
                    if(statusLucky !=='All')
                    {
                        setDataSet(newItem.filter(data=> data.datebuy === dateBuy && data.luckytime === dateTimeLucky && data.status === statusLucky))
                    }
                    else{
                        setDataSet(newItem.filter(data=> data.datebuy === dateBuy && data.luckytime === dateTimeLucky))
                    }
                }
                else{
                    if(statusLucky !=='All')
                    {
                        setDataSet(newItem.filter(data=> data.datebuy === dateBuy && data.luckytime === dateTimeLucky && data.status === statusLucky))
                    }
                    else{
                        setDataSet(newItem.filter(data=> data.datebuy === dateBuy && data.luckytime === dateTimeLucky))
                    }
                }
               
            }
            else if(dateBuy !== null && dateBuy !== undefined && dateBuy !== '')
            {
                if(currentPage>1)
                {
                    setCurrentPage(1)
                    if(statusLucky !=='All')
                    {
                        setDataSet(newItem.filter(data=> data.datebuy === dateBuy && data.status === statusLucky ))
                    }
                    else{
                        setDataSet(newItem.filter(data=> data.datebuy === dateBuy ))
                    }
                }
                else{
                    if(statusLucky !=='All')
                    {
                        setDataSet(newItem.filter(data=> data.datebuy === dateBuy && data.status === statusLucky ))
                    }
                    else{
                        setDataSet(newItem.filter(data=> data.datebuy === dateBuy ))
                    }
                }
            }
            else if(dateLucky !== null && dateLucky !=='' && dateLucky !== undefined )
            {
                console.log("checko no 1= ",newItem);
                if(currentPage>1)
                {
                    setCurrentPage(1)
                    if(statusLucky !=='All')
                    {
                        setDataSet(newItem.filter(data=> data.luckytime === dateTimeLucky && data.status === statusLucky))
                    }
                    else{
                        setDataSet(newItem.filter(data=> data.luckytime === dateTimeLucky))
                    }
                }
                else{
                    if(statusLucky !=='All')
                    {
                        setDataSet(newItem.filter(data=> data.luckytime === dateTimeLucky && data.status === statusLucky))
                    }
                    else{
                        console.log("check lucky time = ",dateTimeLucky)
                        setDataSet(newItem.filter(data=> data.luckytime === dateTimeLucky))
                    }
                }
            }
            else if(statusLucky !== null && statusLucky !=='' && statusLucky !== undefined && statusLucky !=='All')
            {
                if(currentPage>1)
                {
                    setCurrentPage(1)
                    setDataSet(newItem.filter(data=> data.status === statusLucky ))
                }
                else{
                    setDataSet(newItem.filter(data=> data.status === statusLucky ))
                }
               
            }
            else{
                console.log("else no ")
                setDataSet(newItem)
            }   
        }
    }

    // ** get current post
    console.log("check DATASET = ",dataSet)
    const indexOfLastPost = currentPage * postsPerPage;
    const indexOfFirstPost = indexOfLastPost - postsPerPage;
    let currentPosts = dataSet.slice(indexOfFirstPost,indexOfLastPost);
    const paginate = pageNumber =>setCurrentPage(pageNumber)
   console.log("check currentpost = ",currentPosts.length);
    return(
        <div className="mainpage">
            <div className="boxpage" >
                    <div className="title" style={{"textAlign":"center","marginTop":"20px",}}>
                        <h3>รายการหวย ประจำวันที่  </h3>
                    </div>
                    <div className="listitem">
              
                        <table className="table table-bordered table-striped">
                            <thead className="table-secondary">
                                <tr className="table-listitem">
                                    <td colSpan="4" style={{"color":"black"}}>รายการค้นหา</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr className="table-listitem" >
                                <td style={{"color":"white"}}>สถานะการถูกรางวัล</td>
                                    <td>  
                                        <div className=" Action-btn">
                                         
                                           <select className="selectOption" style={{"marginTop":"5px"}} onChange={(e)=>setStatusLucky(e.target.value)}>
                                                <option value = "All">ทั้งหมด</option>
                                                <option value = "Lucky">ถูกรางวัล</option>
                                                <option value = "unLucky">ไม่ถูกรางวัล</option>
                                                {/* <option value = "unLucky">ไม่ถูกรางวัล</option>
                                                <option value = "Lucky">ถูกรางวัล</option> */}
                                            </select>
                                        </div>
                                    </td>
                                    <td  style={{"color":"white"}} >สถานะการจ่ายเงิน</td>
                                    <td>  
                                        <div className=" Action-btn">
                                         
                                           <select className="selectOption" style={{"marginTop":"5px"}} onChange={(e)=>setSelect(e.target.value)}>
                                                <option value = "All">ทั้งหมด</option>
                                                <option value = "No">ยังไม่จ่าย</option>
                                                <option value = "Yes">จ่ายแล้ว</option>
                                                {/* <option value = "unLucky">ไม่ถูกรางวัล</option>
                                                <option value = "Lucky">ถูกรางวัล</option> */}
                                            </select>
                                        </div>
                                    </td>
                                </tr>
                                <tr className="table-listitem">
                                    <td>วันที่ซื้อ</td>
                                    <td>   <div className="datepicker" ><DatePicker className="form-control" name = "datePicker" selected={DateBuy} onChange={(date)=>DatePickert(date)}  /> </div> </td>
                                    <td>งวดประจำวันที่</td>
                                    <td>
                                    <div className="datepicker" ><DatePicker className="form-control" name = "datePicker" selected ={dateLucky} onChange={(date)=>setDateLucky(date)}  /> </div>
                                    </td>
                                </tr>
                                {/* **comment for find number if  i want to use it , can uncomment */}
                                {/* <tr className="table-listitem" style={{"border":"solid white 1px"}} >
                                    <td ><label>เลข</label></td>
                                    <td><input className = "form-control" type="text"/></td>
                                </tr> */}
                                <tr className="table-listitem" style={{"border":"none"}}>
                                    <td colSpan="4" style={{"borderRight":"none","borderLeft":"none"}}>
                                    <div className="setBtn"style={{"display":"flex","margin":"0 auto"}}>
                                        <div className=" Action-btn">
                                            {/* <Link to="/calculate/insert"><button type="button" className="btn btn-light" >เพิ่มข้อมูล</button></Link> */}
                                           {/* <Outlet/> */}
                                            <button type="button" className="btn btn-light"  onClick={()=>setpopup(true)}>เพิ่มข้อมูล</button>
                                        </div>
                                        {/* <div className=" Action-btn">
                                            <button type="button" className="btn btn-light"  onClick={()=>setLuckyModal(true)}>เลขถูก</button>
                                        </div> */}
                                        <div className=" Action-btn">
                                            <button type="button" className="btn btn-primary" onClick={()=>SearchData()}>ค้นหา</button> 
                                        </div>
                                        <div className=" Action-btn">
                                            <button type="button" className="btn btn-warning" onClick = {() =>setIsOpenSendLottaryModal(true)} >ส่งหวย</button> 
                                        </div>
                                    </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
             
                    <div className="listdatanumber" style={{"marginBottom":"15px"}}>
                        <table style={{"border":"solid 2px yellow","width":"95%","margin":"0 auto","marginTop":"30px"}}  className="table table-striped">
                           <thead >
                            <tr style={{"border":"solid 2px white"}}>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>ลำดับ</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>เลข</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>ราคาเลขละ (บาท)</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>ราคาทั้งหมด</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>การแทง</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>สถานะ</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>วันที่ซื้อ</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>เวลา</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>งวดประจำวันที่</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>การจ่าย</td>
                                <td style={{"border":"solid 2px yellow","textAlign":"center"}}>การจัดการ</td>
                            </tr>
                           </thead>
                           <tbody>
                        
                            {
                                
                            currentPosts.length ===0 || currentPosts[0]['idlist'] ==='' ?
                            <tr key = {index}>
                               <td colSpan="11" style={{"textAlign":"center","color":"white"}}>ไม่พบข้อมูล</td>  
                            </tr> : 
                                    currentPosts.map((resp,index)=>{
                                   // console.log(resp.number);
                                   return(
                                     <tr key = {index}>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span>{index+1}</span></td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span>{resp.number}</span></td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span>{resp.price}</span></td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span>{resp.all_price}</span></td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span>{resp.optinpurchase}</span></td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span className={resp.status ==="Lucky" ? "Lucky":"unLucky"}>{resp.status}</span></td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span>{resp.datebuy}</span></td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span>{resp.time}</span></td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span>{resp.luckytime}</span></td>
                                <td  style={{"border":"solid 2px yellow","textAlign":"center","paddingTop":"12px"}}><span className={resp.statuspayment === 'Yes' ? 'paynow':'notpay'}>{resp.statuspayment}</span></td>
                                <td  style={{"border":"solid 2px yellow"}}>
                                    <div className="allbuttom">
                                        <div  className="btn-edit">
                                        <button type="button" className="btn btn-light"  onClick={()=>SetPaymentMethod(resp.idlist)}>แก้ไข</button>
                                        </div>
                                        
                                   
                                        <div className="buttom2"> <button type="button" className="btn btn-danger"  onClick={()=>SetDeleteModal(true,resp.idlist,resp.id)}>ลบ</button></div>
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
            <NumberModal  handleSavingNum={(isSave,dataNum,setUserData,mark,setRank,setTotalPrice) => HandleNumberModal(isSave,dataNum,setUserData,mark,setRank,setTotalPrice)}  show={popup}   />

            {
            // ! no use  this modal , if you want to use this , should  uncomment เลขถูก 
                luckyModal && <InsertLuckyNumberModal  handleSaving = {(status_saving,DataLuckyNumber)=>HandleInsertLuckyNumber(status_saving,DataLuckyNumber)} luckyNumber ={(e)=>setDataLuckyNumber(e)}/>
            }
            {
                // todo paymethod modal changing
            isOpenPaymentModal &&   <PaymentStatusModal HandlePayment = {(status_saving,DataPayment)=>HandlePayment(status_saving,DataPayment)} />
            }
            {
                
            isOpenDeleteModal &&   <DeleteModal onClose={(e) => DeleteData(e)}/>
            }
            {
                isOpenSendLottaryModa && <SendLottaryModal onClose = {(status,date)=>HandleSendLottaryModal(status,date)}/>
            }

        </div>
    );
}

export default Lottary;