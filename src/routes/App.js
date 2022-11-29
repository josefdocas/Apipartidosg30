import '../css/App.css';
import "bootstrap/dist/css/bootstrap.min.css"
import {Navegacion} from "../layouts/Navegacion"
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { PartidosCreados } from '../pages/PartidosCreados';
import { PartidoDetalle } from '../pages/PartidoDetalle';
import "moment/locale/es"

function App() {
  return (
    <BrowserRouter>
        <Navegacion />
        <Routes>
            <Route path='/' element={<PartidosCreados/>} />
            <Route path='/partido/:id' element={<PartidoDetalle/>} />
        </Routes>
    </BrowserRouter>
  );
}

export default App;
