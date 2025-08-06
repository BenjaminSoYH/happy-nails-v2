import React from 'react';
import './App.css';
import {BrowserRouter, Route, Router, Routes} from "react-router-dom";
import ExamplePage from "./pages/ExamplePage";

function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<ExamplePage />} />
        </Routes>
      </BrowserRouter>
  );
}

export default App;
