import axios from "axios"
import jwtDecode from "jwt-decode"
import { usuario } from "../states/sliceReducers"
import { SIGNIN_POST_ENDPOINT } from "./endpoints"
import { setAutenticacionToken } from "./token"


export const autenticacion= (datos)=> dispatch =>{

    return new Promise ((resolver, rechazar)=>{

        axios.post(SIGNIN_POST_ENDPOINT, datos,
            {Headers: {"Accept": "application/json", "Content-Type": "application/json"}}    
        )
        .then(respuesta=>{

            const {authorization}= respuesta.headers

            localStorage.setItem("token", authorization)

            setAutenticacionToken(authorization)

            const decodificado= jwtDecode(authorization)

            dispatch(usuario({conectado: true, usuario: decodificado}))

            resolver(respuesta)
        })
        .catch(err=>{
            rechazar (err)
        })
    })
}

export const cerrarSesion= ()=> dispatch =>{

    localStorage.removeItem("token")

    setAutenticacionToken(false)

    dispatch(usuario({conectado: false, usuario: {}}))
}
