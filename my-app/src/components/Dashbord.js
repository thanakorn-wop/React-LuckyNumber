import React from "react";
import Chart from "chart.js/auto";
import { Line } from "react-chartjs-2";

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
    return(
        <div>
            
            <div style={{"height":"50px","width":"50%","margin":"0 auto","marginTop":"10%"}}>
            <Line data={data} />
            </div>
        
           
        </div>

    );
}
export default Dashbord;