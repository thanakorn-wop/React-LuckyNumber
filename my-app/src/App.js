import './App.css';
import React from 'react';
import { Route, Link, BrowserRouter, Routes,useLocation  } from 'react-router-dom';
import Login from './components/Login';
import Dashbord from './components/Dashbord';
import Navbar from './components/Navbar';
import Lottary from './components/Lottary';
import Report from './components/Report';
import ContactUs from './components/ContactUs';
import { useState } from 'react';
import PangeNotFound from './components/Error/PageNotFound';
import PrivateRoute from './components/PrivateRoute';

function setAuth(authn)
{
  console.log("check authen  = ",authn)
 
  sessionStorage.setItem("token", authn);
}

function getAuth()
{
  const token = sessionStorage.getItem('token');
  return token
}
function App() {
  const auth = getAuth()
  console.log("auth app = ",auth)
   const location = useLocation();
   const showMenu = location.pathname !== '/login';
   console.log("check show menu = ",showMenu)
  // if(!auth)
  // {
  //   return <Login setauth = {setAuth}/>
  // }
  return (
    <> 
  
    {showMenu && <Navbar />}
          <Routes>  
          <Route path='pagenotfound' element={< PangeNotFound />}/>
          <Route path='login' element={< Login   setauth = {setAuth} />}></Route>
              {/* <Route path='/' element={< Outlet />}> */}
              {/* <PrivateRoute path="/dashboard" element={<Dashbord />} /> */}
             <Route path='dashboard' element={<PrivateRoute>  <Dashbord /> </PrivateRoute>}  />
             <Route path='calculate' element={<PrivateRoute>  <Lottary /></PrivateRoute>}  />
             <Route path='report' element={<PrivateRoute>  <Report /></PrivateRoute>}  />
             <Route path='contactus' element={<PrivateRoute>  <ContactUs /></PrivateRoute>}  />
            
          </Routes>
  

    </>

  );
}

export default App;
