import React from 'react';
import './App.css';
import {BrowserRouter, Route, Router, Routes} from "react-router-dom";
import BookCalendar from './pages/BookCalendar';

function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<BookCalendar />} />
        </Routes>
      </BrowserRouter>
  );
}

export default App;
