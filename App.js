import '../css/App.css';
import "bootstrap/dist/css/bootstrap.min.css"
import {Navegacion} from "../layouts/Navegacion"
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { PartidosCreados } from '../pages/PartidosCreados';
import { PartidoDetalle } from '../pages/PartidoDetalle';
import "moment/locale/es"
import { Signup } from '../pages/Signup';
import { Signin } from '../pages/Signin';
import { Provider } from 'react-redux';
import { store } from '../states/store';
import { getAutenticacionToken } from '../connections/token';
import { Mispartidos } from '../pages/Mispartidos';
import { RutaPrivada } from '../components/RutaPrivada';
import { CrearPartido } from '../pages/CrearPartido';
import { EditarPartido } from '../pages/EditarPartido';

getAutenticacionToken()

function App() {
  return (
    <Provider store={store}>
      <BrowserRouter>
          <Navegacion />
          <Routes>
              <Route path='/' element={<PartidosCreados/>} />
              <Route path='/partido/:id' element={<PartidoDetalle/>} />
              <Route path='/signup' element={<Signup />} />
              <Route path='/signin' element={<Signin />} />
              <Route element={<RutaPrivada />}>
                <Route path='/mispartidos' element={<Mispartidos />} />
                <Route path='/crearpartido' element={<CrearPartido />} />
                <Route path='/editarpartido/:id' element={<EditarPartido />} />
              </Route>
          </Routes>
      </BrowserRouter>
    </Provider>
  );
}

export default App;
