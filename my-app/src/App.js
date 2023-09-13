import './App.css';
import 'primeicons/primeicons.css';
import React, { createContext } from 'react';
import { Route, Link, BrowserRouter, Routes,useLocation  } from 'react-router-dom';
import Login from './components/Login';
import Dashbord from './components/Dashbord';
import Navbar from './components/Navbar';
import Lottary from './components/Lottary';
import Summary from './components/Summary';
import ContactUs from './components/ContactUs';
import { useState } from 'react';
import PangeNotFound from './components/Error/PageNotFound';
import PrivateRoute from './components/PrivateRoute';
import Management from "../src/components/Manamgent"
import Report from "../src/components/Report"
import InternalServer from './components/Error/InternalServer';


function getAuth()
{
  const token = sessionStorage.getItem('token');
  return token
}
function App() {
  const auth = getAuth()
  const [userRole,setUserRole] = useState("");
  const location = useLocation();
  const showMenu = location.pathname !== '/login';
  function setAuth(data)
  {
    sessionStorage.setItem("token", data.token);
    localStorage.setItem("userRole",data.role)
   // console.log("setauth = ",data.header)
  }
  // if(!auth)
  // {
  //   return <Login setauth = {setAuth}/>
  // }
  return (
    <> 
  
    {showMenu && <Navbar />}
          <Routes>  
          <Route path='pagenotfound' element={< PangeNotFound />}/>
          <Route path='internalserver' element={< InternalServer />}/>
          <Route path='login' element={< Login   setauth = {setAuth} />}></Route>
              {/* <Route path='/' element={< Outlet />}> */}
              {/* <PrivateRoute path="/dashboard" element={<Dashbord />} /> */}
             <Route path='dashboard' element={<PrivateRoute>  <Dashbord /> </PrivateRoute>}  />
             <Route path='calculate' element={<PrivateRoute>  <Lottary /></PrivateRoute>}  />
             <Route path='manage' element={<PrivateRoute>  <Management /></PrivateRoute>}  />
             <Route path='summary' element={<PrivateRoute>  <Summary /></PrivateRoute>}  />
             <Route path='contactus' element={<PrivateRoute>  <ContactUs /></PrivateRoute>}  />
             <Route path='report' element={<PrivateRoute>  <Report /></PrivateRoute>}  />
            
          </Routes>
  

    </>

  );
}

export default App;
