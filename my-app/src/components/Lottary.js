import React, { useEffect, useState,useRef  } from "react";
import "../CSS/Lottary.css"
import NumberModal from "./Modal/NumberModal"
import * as urlConstant from "../components/Constant/UrlConstant"
import InsertLuckyNumberModal from "./Modal/InsertLuckyNumberModal";
import PaymentStatusModal from "../components/Modal/PaymentStatusModal"
import DeleteModal from "./Modal/DeleteDataModal"
import DatePicker from "react-datepicker";
import {  useNavigate } from "react-router-dom";
import SendLottaryModal from "./Modal/SendLottaryModal";
import LotteryModal from "./Modal/LotteryModal";
import axios from 'axios';
import ReactPaginate from 'react-paginate';
import PaginatedItems from "../components/PaginatedItems"
import { Messages } from 'primereact/messages';
import {useMessage} from './Constant/useMessage'
import { BlockUI } from 'primereact/blockui';
import { ProgressSpinner } from 'primereact/progressspinner';
import Loading from "./Constant/Loading";
function Lottary()
{
    let navigate = useNavigate();
    const msgs = useRef(null);
    const blockRef = useRef(null)
    const addMessage = useMessage();
    const [isOpenMessage,setIsOpenMessage] = useState(false);
    const [msgWarning,setMsgWaring] = useState("");
    const [process,setProcess] = useState(false);
    const [add,setAdd] = useState(0);
    const [popup,setpopup] =  useState(false);
    const [blocked, setBlocked] = useState(false);
    const [status,setStatus] = useState();
    const [luckyModal,setLuckyModal]  =useState(false);
    const [isOpenPaymentModal,setIsOpenPayMentModal] = useState(false);
    const [isOpenDeleteModal,setIsOpenDeleteModal] = useState(false);
    const [isOpenSendLottaryModal,setIsOpenSendLottaryModal] = useState(false);
    const [isOpenLotteryModal,setIsOpenLotteryModal] = useState(false);
    const [DateBuy, setDateBuy] = useState("");
    const [DateSelect,setDataSelect] = useState(new Date());
    const [IsSelect,setIsSelect] = useState(false);
    const [dateLucky,setDateLucky] = useState("");
    const [dataSet,setDataSet] = useState([{idlist:"",number:"",price:"",allPrice:"",optionpurchase:"",status:"",datebuy:"",time:"",statuspayment:"",luckytime:"",id:"",sequence:""}]);
    const [newItem,setNewItem] = useState([{idlist:"",number:"",price:"",allPrice:"",optionpurchase:"",status:"",datebuy:"",time:"",statuspayment:"",luckytime:"",id:"",sequence:""}]);
    const [sessionUser,setSession] = useState(sessionStorage.getItem("token"));
    const [DataLuckyNumber,setDataLuckyNumber] = useState();
    const [index,setIndex] = useState();
    const [no,setNo] = useState(null);
    const [selectPayment ,setSelecPaymentt] = useState("All");
    const [statusLucky,setStatusLucky] = useState("All");
    const [statusProcess,setStatusProcess] = useState("");
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
                    console.log("test useEffect ")
                    let date = null;
                 
                        date = "lastdata";
                          try{
                            const response = await axios.get(urlConstant.GET_LIST_LOTTARY+date,{
                                headers: { 'Content-Type': 'application/json' }
                                
                            })
                            // console.log("check response.data  = ",response );
                            // console.log("check response.data  = ",response.data );
                            if(response.data != undefined && response.data.datalist != null)
                            {
                                console.log("response get data = ",response.data)
                                setDataSet(response.data.datalist)
                               // console.log("data = ",response.data.datalist)
                                // setDateLucky(response.data.datalist.luckytime)
                                // setNewItem(response.data.datalist)   
                            }
                            if(isOpenMessage)
                            {
                                msgs.current.clear();
                                addMessage(msgs,statusProcess,<b>{msgWarning}</b>)
                                setProcess(false)
                                setIsOpenMessage(false)
                                setStatusProcess("")
                                setMsgWaring("")
                            }
                           // console.log("check response data = ",response );
                        }catch(error)
                        {
                            console.error(error);
                        }
                  }
                getListitem();
    },[process])
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
       // console.log("check status delete = ",DataDelete);
        if(status ==='Yes')
        {
            try{
                const response =  axios.post(urlConstant.POST_DELETEDATAT,DataDelete,{
                    headers: { 'Content-Type': 'application/json' }
                }).then((resp)=>{
                    // console.log(resp)
                    if(resp.status=== 200)
                    {

                        setProcess(true)
                        setIsOpenDeleteModal(false)
                        setMsgWaring(resp.data.message)
                        setStatusProcess(resp.data.statusProcess)
                        setIsOpenMessage(true)
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
        //console.log("save InsertNUmber = ",e,DataLuckyNumber);
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
        console.log("check index3 = ",newItem)
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
                    // reload()    
                }
                else{
                    alert("ทำรายการไม่สำเร็จ")
                }
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
      //  console.log("Datanum lottay = ",dataNum)
        let statusValidate = true;
        if(isSave)
        {
            if(mark !== '' && mark !== null && mark !== undefined)
            {
                if(mark >0)
                {
                    for(let i = 0; i<dataNum.dataSet.length; i++)
                    {
                        let arrNo = [];
                        dataNum.dataSet[i].sequence = mark;
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
                          //  console.log("check number = ",number);
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
                                           // console.log("check qq = ",number[i])
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
                //console.log("check last data = ",dataNum)
                if(answer)
                {
                    dataNum.dataSet.map((data,index)=>{
                        // console.log("data.date = ",data.date)
                        let date = data.date
                        // console.log("data after toISOString = ",date)
                        let lucky_date = data.luckytime
                         date = date.getFullYear()+"-"+(1+Number(date.getMonth()))+"-"+date.getDate();
                         lucky_date =  lucky_date.getFullYear()+"-"+(1+Number(lucky_date.getMonth()))+"-"+lucky_date.getDate();
                       // console.log("date = ",date);
                        data.date = date;
                        data.luckytime = lucky_date;
                        // console.log("data = ",data)
                            
                    })
                    try{
                        const Post_Insert_Number = await axios.post(urlConstant.POST_INSERT_NUMBER,dataNum,{
                            headers: { 'Content-Type': 'application/json' }
                        }).then(res =>{
                                // console.log("check response num = ",res.data)
                        if(res.data.statusProcess === false && res.data.statusCode === '01')
                        {
                          
                            setpopup(false);
                            setRank(0)
                            blockRef.current.block()
                            setTimeout(() => {
                            msgs.current.clear();
                        // setMsgWaring("ทำรายการสำเร็จ");
                            blockRef.current.unBlock()
                            setIsOpenLotteryModal(false)
                            addMessage(msgs,res.data.statusProcess,<b>{res.data.message}</b>)
                            }, 500);
                            setUserData({dataSet:[{
                                id: add,
                                date: new Date(),
                                option:"",
                                number:"",
                                price:"",
                                allPrice:"",
                                idLine:"",
                                phoneNumber:"",
                                luckytime:new Date(),
                                set:""
                        
                            }]})
                        }
                        else{
            

                            setpopup(false);
                            setMsgWaring("ทำรายการสำเร็จ")
                            setProcess(true);
                            setStatusProcess(true);
                            setIsOpenMessage(true)
                            setRank(0)
                            setUserData({dataSet:[{
                                id: add,
                                date: new Date(),
                                option:"",
                                number:"",
                                price:"",
                                allPrice:"",
                                idLine:"",
                                phoneNumber:"",
                                luckytime:new Date(),
                                set:""
                        
                            }]})
                            //  addMessage(msgs,200,res.data.message)
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
                allPrice:"",
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
    async function HandleValidateLottaryModal(date,status,empty,setEmpty)
    {
        // let obj = {date:date}
        console.log("date = ",setEmpty);
        if(status)
        {
            if(empty)
            {
                alert("กรุณารอหวยออก");
                setEmpty(false)
            }
            else{
                const postItem = await axios.post(urlConstant.POST_VALIDATE_LOTTARY,date,{
                    headers: { 'Content-Type': 'application/json' }
                })
                if(postItem !== null && postItem !== undefined)
                {
                    console.log("check postItem = ",postItem.data)
                    if(postItem.data.statusCode == '01' && postItem.data.statusProcess === true)
                    {
                        // alert("ทำรายการสำเร็จ")
                        blockRef.current.block()
                        setTimeout(() => {
                        msgs.current.clear();
                    // setMsgWaring("ทำรายการสำเร็จ");
                        blockRef.current.unBlock()
                        setIsOpenLotteryModal(false)
                      //  addMessage(msgs,postItem.data.statusProcess,<b>{postItem.data.message}</b>)
                        setProcess(true)
                        setMsgWaring(postItem.data.message)
                        setStatusProcess(postItem.data.statusProcess)
                        setIsOpenMessage(true)
                }, 500);
                        // reload()
                    }
                    else{
                        alert("ไม่มีรายการให้ทำ")
                    }
                }
            } 
        }
        else{
            setIsOpenLotteryModal(false)
        }
    }
    async function HandleSendLottaryModal(status,date)
    {
       // console.log("check status sending = ",status,date)

        if(status ==="Yes")
        {
            const response = await axios.post(urlConstant.POST_SEND_LOTTARY,date,{
                headers: { 'Content-Type': 'application/json' }
            })
           // console.log("check response sendding  ",response);
            if(response.data.message ==="empty")
            {
                alert("ไม่มีข้อมูลให้ทำรายการ")
            }
            else if(response.data.message ==="duplicate_data")
            {
                // alert("ไม่สามารถทำรายการซ้ำได้");
                setMsgWaring("ไม่สามารถทำรายการซ้ำได้");
                setProcess(true)
            }else if(response.data.message === "not_time_to_work")
            {
                alert("กรุณารอหวยออก");
            }
            else if(response.data.message ==="validate")
            {
                alert("กรุณาตรวจหวยก่อนทำรายการ")
            }
            else
            {
                // alert("ทำรายการสำเร็จ");
                // console.log("process = ",process)
                setIsOpenSendLottaryModal(false);
                blockRef.current.block()
                setTimeout(() => {
                    msgs.current.clear();
                    // setMsgWaring("ทำรายการสำเร็จ");
                    blockRef.current.unBlock()
                    addMessage(msgs,response.status,<b>ทำรายการสำเร็จ</b>)
                }, 500);
               
               
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
        let dateTimeLucky = null;
        if(dateLucky !== null && dateLucky !== '' && dateLucky !== undefined)
        {
            dateTimeLucky = (dateLucky.getFullYear()+"-"+String(1+Number(dateLucky.getMonth())).padStart(2, '0')+"-"+String(dateLucky.getDate()).padStart(2, '0'));
        }
      
        if(selectPayment !== "All" && statusLucky !=='All' )
        {
         //   console.log("date buy = ",dateTimeLucky)
            //** use object.values because the output is [Object,Object] */
                if(dateTimeLucky !== null  )
                {
                  
                    if(no !== null )
                    {
                       // console.log("check 1 = ",no)
                      //  console.log("check newItem = ",dataSet.filter(data=> data.statuspayment === selectPayment &&  data.status === statusLucky && data.sequence === no  && data.luckytime === dateTimeLucky))
                       setDataSet(dataSet.filter(data=> data.statuspayment === selectPayment &&  data.status === statusLucky && data.sequence === no && data.luckytime === dateTimeLucky))
                    }
                    else{
                        setDataSet(dataSet.filter(data=> data.statuspayment === selectPayment &&  data.status === statusLucky && data.luckytime === dateTimeLucky))
                    }
                        
                }
                else
                {
                    if(no !== null )
                    {
                       // console.log("check ถูกรางงวัลไม่จ่าย = ",no)
                        //console.log("check newItem = ",dataSet.filter(data=> data.statuspayment === selectPayment &&  data.status === statusLucky && data.sequence === no ))
                       setDataSet(dataSet.filter(data=> data.statuspayment === selectPayment &&  data.status === statusLucky && data.sequence === no))
                    }
                    else{
                        setDataSet(dataSet.filter(data=> data.statuspayment === selectPayment &&  data.status === statusLucky))
                    }
                }
        }
        else if (selectPayment !== "All" && statusLucky ==='All' ){
            if(dateTimeLucky !== null  )
            {
              
                if(no !== null )
                {
                   // console.log("check 1 = ",no)
                  //  console.log("check newItem = ",dataSet.filter(data=> data.statuspayment === selectPayment &&  data.status === statusLucky && data.sequence === no  && data.luckytime === dateTimeLucky))
                   setDataSet(dataSet.filter(data=> data.statuspayment === selectPayment  && data.sequence === no && data.luckytime === dateTimeLucky))
                }
                else{
                    setDataSet(dataSet.filter(data=> data.statuspayment === selectPayment  && data.luckytime === dateTimeLucky))
                }
                    
            }
            else
            {
                if(no !== null )
                {
                    //console.log("check ถูกรางงวัลไม่จ่าย = ",no)
                   // console.log("check newItem = ",dataSet.filter(data=> data.statuspayment === selectPayment  && data.sequence === no ))
                   setDataSet(dataSet.filter(data=> data.statuspayment === selectPayment &&   data.sequence === no))
                }
                else{
                    setDataSet(dataSet.filter(data=> data.statuspayment === selectPayment))
                }
            }

        }
        else if (selectPayment === "All" && statusLucky !=='All' )
        {
            if(dateTimeLucky !== null  )
            {
              
                if(no !== null )
                {
                    //console.log("check 1 = ",no)
                  //  console.log("check newItem = ",dataSet.filter(data=> data.statuspayment === selectPayment &&  data.status === statusLucky && data.sequence === no  && data.luckytime === dateTimeLucky))
                   setDataSet(dataSet.filter(data=> data.sequence === no && data.luckytime === dateTimeLucky && data.status === statusLucky))
                }
                else{
                    setDataSet(dataSet.filter(data=> data.luckytime === dateTimeLucky && data.status === statusLucky ))
                }
                    
            }
            else
            {
                if(no !== null )
                {
                   // console.log("check ถูกรางงวัลไม่จ่าย = ",no)
                   // console.log("check newItem = ",dataSet.filter(data=> data.statuspayment === selectPayment  && data.sequence === no ))
                   setDataSet(dataSet.filter(data=> data.status === statusLucky &&   data.sequence === no))
                }
                else{
                    setDataSet(dataSet.filter(data=> data.status === statusLucky))
                }
            }
        }
        else{
            if(dateTimeLucky !== null  )
            {
              
                if(no !== null )
                {
                   setDataSet(dataSet.filter(data=> data.sequence === no && data.luckytime === dateTimeLucky))
                }
                else{
                    setDataSet(dataSet.filter(data=> data.luckytime === dateTimeLucky  ))
                }         
            }
            else
            {
                if(no !== null )
                {
                   setDataSet(dataSet.filter(data=>    data.sequence === no))
                }       
            }
        }
   
    }

    return(
        <div className="mainpage">
            <div className="boxpage" >
                    <div className="title" style={{"textAlign":"center","marginTop":"20px",}}>
                        <h3>รายการหวย ประจำวันที่  </h3>
                    </div>
                    <div className="listitem">
                    <Messages ref={msgs} />
                        <table className="table table-bordered table-striped">
                            <thead className="table-secondary">
                                <tr className="table-listitem">
                                    <td colSpan="4" style={{"color":"black"}}>รายการค้นหา</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr className="table-listitem" >
                                <td style={{"color":"white"}}><span>สถานะการถูกรางวัล</span></td>
                                    <td>  
                                        <div className=" Action-btn">
                                         
                                           <select className="selectOption" style={{"marginTop":"5px"}} onChange={(e)=>setStatusLucky(e.target.value)}>
                                                <option value = "All">ทั้งหมด</option>
                                                <option value = "lucky">ถูกรางวัล</option>
                                                <option value = "unLucky">ไม่ถูกรางวัล</option>
                                                {/* <option value = "unLucky">ไม่ถูกรางวัล</option>
                                                <option value = "Lucky">ถูกรางวัล</option> */}
                                            </select>
                                        </div>
                                    </td>
                                    <td  style={{"color":"white"}} ><span>สถานะการจ่ายเงิน</span></td>
                                    <td>  
                                        <div className=" Action-btn">
                                         
                                           <select className="selectPayment" style={{"marginTop":"5px"}} onChange={(e)=>setSelecPaymentt(e.target.value)}>
                                                <option value = "All">ทั้งหมด</option>
                                                <option value = "No">ยังไม่จ่าย</option>
                                                <option value = "Yes">จ่ายแล้ว</option>
                                                {/* <option value = "unLucky">ไม่ถูกรางวัล</option>
                                                <option value = "Lucky">ถูกรางวัล</option> */}
                                            </select>
                                        </div>
                                    </td>
                                </tr>
                                <tr className="table-listitem" style={{"color":"white"}}>
                                    <td><span>ชุดที่</span></td>
                                    <td>   <div className="no" ><input type="number" className="form-control" onChange={(data)=>setNo(data.target.value)}/> </div> </td>
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
                                    <div className="setBtn"style={{"display":"flex"}}>
                                    <div className=" Action-btn">
                                            <button type="button" className="btn btn-primary" onClick={()=>SearchData()}>ค้นหา</button> 
                                        </div>
                                        <div className=" Action-btn">
                                            {/* <Link to="/calculate/insert"><button type="button" className="btn btn-light" >เพิ่มข้อมูล</button></Link> */}
                                           {/* <Outlet/> */}
                                            <button type="button" className="btn btn-light"  onClick={()=>setpopup(true)}>เพิ่มข้อมูล</button>
                                        </div>
                                        {/* <div className=" Action-btn">
                                            <button type="button" className="btn btn-light"  onClick={()=>setLuckyModal(true)}>เลขถูก</button>
                                        </div> */}
                                        <div className=" Action-btn">
                                            <button type="button" className="btn btn-warning" onClick = {() =>setIsOpenSendLottaryModal(true)} >ส่งหวย</button> 
                                        </div>
                                        <div className="Action-btn">
                                            <button type="button" className="btn btn-success" onClick = {() =>setIsOpenLotteryModal(true)}  >ตรวจหวย</button> 
                                        </div>
                                    </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <Loading ref={blockRef}/>
                    <div className="listdatanumber" style={{"marginBottom":"15px","display":"flex","flexDirection":"column"}}>
                        <table style={{"width":"95%","margin":"0 auto","marginTop":"30px","marginBottom":"15px","color":"white"}}  className="table">
                           <thead >
                            <tr style={{"border":"solid 2px white"}}>
                                <td style={{"textAlign":"center"}}>ลำดับ</td>
                                <td style={{"textAlign":"center"}}>เลข</td>
                                <td style={{"textAlign":"center"}}>ราคาเลขละ (บาท)</td>
                                <td style={{"textAlign":"center"}}>ราคาทั้งหมด</td>
                                <td style={{"textAlign":"center"}}>การแทง</td>
                                <td style={{"textAlign":"center"}}>สถานะ</td>
                                <td style={{"textAlign":"center"}}>วันที่ซื้อ</td>
                                <td style={{"textAlign":"center"}}>เวลา</td>
                                <td style={{"textAlign":"center"}}>งวดประจำวันที่</td>
                                <td style={{"textAlign":"center"}}>ชุดที่</td>
                                <td style={{"textAlign":"center"}}>การจ่าย</td>
                                <td style={{"textAlign":"center"}}>การจัดการ</td>
                            </tr>
                           </thead>
                           <tbody>
                        
                            {
                                
                            newItem.length ===0 || newItem[0]['idlist'] ==='' ?
                            <tr key = {index}>
                               <td colSpan="12" style={{"textAlign":"center","color":"white"}}><span>ไม่พบข้อมูล</span></td>  
                            </tr> : 
                                    newItem.map((resp,index)=>{
                                   // console.log(resp.number);
                                   return(
                                     <tr key = {index} style={{"border":"solid white 2px"}} >
                                <td  style={{"textAlign":"center","paddingTop":"12px"}}><span>{index+1}</span></td>
                                <td  style={{"textAlign":"center","paddingTop":"12px"}}><span>{resp.number}</span></td>
                                <td  style={{"textAlign":"center","paddingTop":"12px"}}><span>{resp.price}</span></td>
                                <td  style={{"textAlign":"center","paddingTop":"12px"}}><span>{resp.allPrice}</span></td>
                                <td  style={{"textAlign":"center","paddingTop":"12px"}}><span>{resp.optinpurchase}</span></td>
                                <td  style={{"textAlign":"center","paddingTop":"12px"}}><span className={resp.status ==="lucky" ? "lucky":"unLucky"}>{resp.status === 'lucky' ?'ถูกรางวัล':"ไม่ถูกรางวัล"}</span></td>
                                <td  style={{"textAlign":"center","paddingTop":"12px"}}><span>{resp.datebuy}</span></td>
                                <td  style={{"textAlign":"center","paddingTop":"12px"}}><span>{resp.time}</span></td>
                                <td  style={{"textAlign":"center","paddingTop":"12px"}}><span>{resp.luckytime}</span></td>
                                <td  style={{"textAlign":"center","paddingTop":"12px"}}><span>{resp.sequence}</span></td>
                                <td  style={{"textAlign":"center","paddingTop":"12px"}}><span className={resp.statuspayment === 'Yes' ? 'paynow':'notpay'}>{resp.statuspayment}</span></td>
                                <td style={{"textAlign":"center","paddingTop":"12px"}}>
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
                        {/* <Paginagtion   totalPosts={dataSet.length} postsPerPage = {postsPerPage} paginate={paginate} /> */}
                        <div className="paginatedLottary "  style={{"position":"relative","zIndex":"1","margin":"0 auto"}}>
                                <PaginatedItems items = {dataSet} callBackItem ={(items)=>setNewItem(items) } />
                        </div>
                        
                </div>
            </div>
            {/* // list insert purachse number  modal */}
            <NumberModal  handleSavingNum={(isSave,dataNum,setUserData,mark,setRank,setTotalPrice) => HandleNumberModal(isSave,dataNum,setUserData,mark,setRank,setTotalPrice)}  show={popup}   />

            {/* {
            // ! no use  this modal , if you want to use this , should  uncomment เลขถูก 
                luckyModal && <InsertLuckyNumberModal  handleSaving = {(status_saving,DataLuckyNumber)=>HandleInsertLuckyNumber(status_saving,DataLuckyNumber)} luckyNumber ={(e)=>setDataLuckyNumber(e)}/>
            } */}
            {
                // todo paymethod modal changing
            isOpenPaymentModal &&   <PaymentStatusModal HandlePayment = {(status_saving,DataPayment)=>HandlePayment(status_saving,DataPayment)} />
            }
            {
                
            isOpenDeleteModal &&   <DeleteModal onClose={(e) => DeleteData(e)}/>
            }
            {
                isOpenSendLottaryModal && <SendLottaryModal onClose = {(status,date)=>HandleSendLottaryModal(status,date)}/>
            }
            {
                isOpenLotteryModal && <LotteryModal onSave={(date,status,empty,setEmpty) => HandleValidateLottaryModal(date,status,empty,setEmpty)}/>
            }

        </div>
    );
}

export default Lottary;