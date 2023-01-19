import React, { useContext, useEffect, useState } from "react";
import Chart, { DatasetController } from "chart.js/auto";
import { Line } from "react-chartjs-2";
import axios from "axios";
import * as urlConstant from "../components/Constant/UrlConstant"
import { useNavigate } from "react-router-dom";
import { AuthContext } from "./Authen";

function Dashbord()
{
  let session =  sessionStorage.getItem("token");
  const [dataSet, setDataSet] = useState([]);
  const [balanceEachMonth,setBalanaceEachMonth] = useState([]);
  const qq = [];

  //console.log("check array = ",balanceEachMonth);
  if(session === null || session === undefined || session ==="")
  {
    window.location.assign("/login")
  }
   const {auth,setauth} = useContext(AuthContext);
  // console.log("auth = ",auth);
  // console.log(auth.accessToken)
  let navaigate = useNavigate();
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
  useEffect(()=>{
  try{
    axios.get(urlConstant.GET_DASH_BOARD,null,{headers:{'Content-Type':'application/json' }}).then(res=>{
      console.log(" tt = ",session)
      if(res.data.statusCode ==='401')
      {
        navaigate("/login");
      }else{
        console.log("check data ",res.data.dataList.years)
        setDataSet(res.data.dataList)
      }
    })
   
  }catch(e)
  {
    if(e.res.status ===401)
    {
     navaigate("/login");
    }
  }
},[])

let sum = 0;
let arr = [];
let strmonth = "";
// dataSet.map((data,index)=>{
//   console.log(data.time.split("-"));
// })
Object.keys(dataSet).map((key,index)=>{
   if(key !== "years" && key !== 'id_month' && key !== "id")
   {
     arr.push(dataSet[key])
   }
})
console.log("check arr = ",arr);
if(dataSet !== [] || dataSet !== null || dataSet !== undefined)
{
  //console.log("check data list = ",dataSet)
  // for(let i = 1 ; i <=12; i++)
  // {
  //   dataSet.map((data,index)=>{
  //      strmonth = data.time.split("-");
  //      if(i == strmonth[1])
  //      {
  //       sum = sum+Number(data.balance);
  //      }
  //   })
  //   arr.push(sum);
  //   sum = 0;
  // }
  // dataSet.map((data,index)=>{
  //   console.log("check month = ",data);
  // })
}
//console.log("check arr after loop = ",arr);
const labels = ["January", "February", "March", "April", "May", "June","July","August","September","October","November","December"];
const data = {
  labels: labels,
  datasets: [
    {
      label: "Balance",
      backgroundColor: "red",
      borderColor: "blue",
      data: arr,
     
      
    },
  ],
};

    return(
        <div className="dashboardpage" style={{"marginTop":"5%","display":"flex","flexDirection":"column","textAlign":"center"}}>
            <div className="titile" style={{"textAlign":"center"}}>
                <h3>กราฟสถิติการทำรายได้ตลอดทั้งปี</h3>
            </div>
            <div className="bc">
              <select>
                <option>sss</option>
              </select>
            </div>
            <div style={{"width":"60%","margin":"0 auto","position":"relative","top":"20px"}}>
            <Line data={data} style={{"backgroundColor":"rgb(248,245,252)"}} />
            </div>
        
           
        </div>

    );
}
export default Dashbord;