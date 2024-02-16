import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter} from 'react-router-dom';
import Header from './componentes/Header/Header.js';
import RotaProtegida from './componentes/RotaProtegida/RotaProtegida.js';
import 'materialize-css/dist/css/materialize.min.css';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
    <Header />
      <RotaProtegida />
    </BrowserRouter>
  </React.StrictMode>
);