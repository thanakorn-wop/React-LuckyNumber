
import './App.css';
import { Route, Link, BrowserRouter,Routes } from 'react-router-dom'  
import Login  from './components/Login';
import Dashbord  from './components/Dashbord';
import Navbar from './components/Navbar';
import Lottary from './components/Lottary';
import Report from './components/Report';
import ContactUs from './components/ContactUs';
import {AuthContext} from "./components/Authen"
import { useState } from 'react';
import Outlet from './components/Outlet';
import PangeNotFound from './components/Error/PageNotFound';
function App() {
  const [auth , setauth] = useState();
  return (
    <> 
 
 <BrowserRouter>
 {/* <Navbar/> */}
  <AuthContext.Provider value = {{auth,setauth}}>

    <Outlet/>
          <Routes>
          <Route path='pagenotfound' element={< PangeNotFound />}></Route>
          <Route path='login' element={< Login />}></Route>
              {/* <Route path='/' element={< Outlet />}> */}
              <Route path='dashboard' element={< Dashbord />}></Route>
              <Route path='calculate' element={< Lottary />}></Route>
              <Route path='report' element={< Report />}></Route>
              <Route path='contactus' element={< ContactUs />}></Route>
              {/* </Route> */}
          </Routes>
      </AuthContext.Provider>
    </BrowserRouter>
    </>

  );
}

export default App;
