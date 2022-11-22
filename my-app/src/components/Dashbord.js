import React, { useEffect } from "react";
import Chart from "chart.js/auto";
import { Line } from "react-chartjs-2";
import axios from "axios";
import * as urlConstant from "../components/Constant/UrlConstant"
import { useNavigate } from "react-router-dom";
const labels = ["January", "February", "March", "April", "May", "June","July","August","September","October","November","December"];


const data = {
  labels: labels,
  datasets: [
    {
      label: "My First dataset",
      backgroundColor: "black",
      borderColor: "yellow",
      data: [0, 10, 5, 2, 20, 30, 45,1,20,40,50,60],
      
    },
  ],
};

function Dashbord()
{
  let session =  sessionStorage.getItem("token");
  const h = new Headers();
  h.append('Content-Type','application/json')
  h.append('token',session)
  let navaigate = useNavigate();
    axios.interceptors.request.use(
      config =>{
        config.headers.Authorization = `Bearer ${session}`;
        return config;
      }
    )

  if(session == null || session == undefined)
  {
    navaigate("/login");
  }
  useEffect(()=>{
    
  try{
    axios.get(urlConstant.GET_DASH_BOARD,null,{headers:{'Content-Type':'application/json' }}).then(res=>{
      console.log(res.data)
      if(res.data.statusCode ==='401')
      {
        navaigate("/login");
      }
    })
   
  }catch(e)
  {
    console.error("Error response Dashboard  = ",e);
    console.log("check error = ",e.response.status);
    if(e.resp.status ===401)
    {
     navaigate("/login");
    }
  }

},[])
    return(
        <div>
            
            <div style={{"height":"50px","width":"50%","margin":"0 auto","marginTop":"10%"}}>
            <Line data={data} />
            </div>
        
           
        </div>

    );
}
export default Dashbord;