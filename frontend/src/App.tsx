import React from 'react';
import './App.css';
import {BrowserRouter, Route, Router, Routes} from "react-router-dom";
import AppointmentMainPage from "./pages/AppointmentMainPage";
import BookCalendar from './pages/BookCalendar';
import ServiceStaffPage from "./pages/ServiceStaffPage";

function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<AppointmentMainPage />} />
          <Route path="/" element={<BookCalendar />} />
            <Route path="/service/:serviceid" element={<ServiceStaffPage/>}/>
        </Routes>
      </BrowserRouter>
  );
}

export default App;
