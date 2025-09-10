import React from 'react';
import './App.css';
import {BrowserRouter, Route, Router, Routes} from "react-router-dom";
import ExamplePage from "./pages/ExamplePage";
import TimeSlots from './pages/components/TimeSlots';

function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<TimeSlots />} />
        </Routes>
      </BrowserRouter>
  );
}

export default App;
