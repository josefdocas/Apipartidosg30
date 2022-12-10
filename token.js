import axios from "axios"
import jwtDecode from "jwt-decode"
import { usuario } from "../states/sliceReducers"
import { store } from "../states/store"
import { cerrarSesion } from "./usuarioAcciones"


export const setAutenticacionToken= (token)=>{
    if(token){
        axios.defaults.headers.common["Authorization"]= token
    }else{
        delete axios.defaults.headers.common["Authorization"]
    }
}

export const getAutenticacionToken= ()=>{

    if(localStorage.token){

        setAutenticacionToken(localStorage.token)

        const decodificado= jwtDecode(localStorage.token)

        store.dispatch(usuario({conectado: true, usuario: decodificado}))

        const tiempoActual= Math.floor(Date.now() / 1000)

        if(decodificado.exp < tiempoActual){
            store.dispatch(cerrarSesion())
            window.location.href= "/signin"
        }

    }
}