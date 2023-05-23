import './App.css';
import React, { createContext } from 'react';
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
import AuthenProvider  from './components/Authen/AuthenProvider';
export const AuthContext = createContext();


// function setAuth(data)
// {
 
//   sessionStorage.setItem("token", data.token);
 
//   console.log("check auth = ",data.token)
//   console.log("check auth = ",data)
// }

function getAuth()
{
  const token = sessionStorage.getItem('token');
  return token
}
function App() {
  const auth = getAuth()
  const [userRole,setUserRole] = useState("");
  
    console.log("auth app = ")
  const location = useLocation();
  const showMenu = location.pathname !== '/login';
  function setAuth(data)
  {
    sessionStorage.setItem("token", data.token);
    localStorage.setItem("userRole",data.role)
    console.log("setauth = ",data.header)
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
          <Route path='login' element={< Login   setauth = {setAuth} />}></Route>
              {/* <Route path='/' element={< Outlet />}> */}
              {/* <PrivateRoute path="/dashboard" element={<Dashbord />} /> */}
             <Route path='dashboard' element={<PrivateRoute>  <Dashbord /> </PrivateRoute>}  />
             <Route path='calculate' element={<PrivateRoute>  <Lottary /></PrivateRoute>}  />
             <Route path='summary' element={<PrivateRoute>  <Report /></PrivateRoute>}  />
             <Route path='contactus' element={<PrivateRoute>  <ContactUs /></PrivateRoute>}  />
            
          </Routes>
  

    </>

  );
}

export default App;
