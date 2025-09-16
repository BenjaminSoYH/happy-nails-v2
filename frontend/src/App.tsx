import React from 'react';
import './App.css';
import {BrowserRouter, Route, Router, Routes} from "react-router-dom";
import AppointmentMainPage from "./pages/AppointmentMainPage";
import BookCalendar from './pages/BookCalendar';
import StaffPage from "./pages/StaffPage";

function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<AppointmentMainPage />} />
          <Route path="/nailtechs" element={<StaffPage/>}/>
          <Route path="/hello" element={<BookCalendar />} />
        </Routes>
      </BrowserRouter>
  );
}

export default App;
