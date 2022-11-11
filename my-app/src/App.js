
import './App.css';
import { Route, Link, BrowserRouter,Routes } from 'react-router-dom'  
import Login  from './components/Login';
import Dashbord  from './components/Dashbord';
import Navbar from './components/Navbar';
import Lottary from './components/Lottary';
import Report from './components/Report';
import ContactUs from './components/ContactUs';
function App() {
  return (
    <> 
 
 <BrowserRouter>
    <Navbar/>
          <Routes>
            {/* <Navbar /> */}
              {/* <Route path='/' element={< Home />}></Route> */}
              {/* <Route path='/' element={< Navbar />}> */}
              <Route path='dashboard' element={< Dashbord />}></Route>
              <Route path='calculate' element={< Lottary />}></Route>
              <Route path='report' element={< Report />}></Route>
              <Route path='contactus' element={< ContactUs />}></Route>
              {/* <Route path='/user' element={< User />}></Route>
              <Route path='/userList' element={< UserList />}></Route>
              <Route path='/editUser' element={< UpdateUser />}></Route> */}
           
              <Route path='/login' element={< Login />}></Route>
           {/* </Route> */}
          
          </Routes>

      
    </BrowserRouter>
    </>

  );
}

export default App;
