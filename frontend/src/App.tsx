import React from 'react';
import './App.css';
import {BrowserRouter, Route, Router, Routes} from "react-router-dom";
import AppointmentMainPage from "./pages/AppointmentMainPage";


function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<AppointmentMainPage />} />
        </Routes>
      </BrowserRouter>
  );
}

export default App;
