import axios from "axios"
import { Button } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import { ELIMINARPARTIDO_DELETE_ENDPOINT } from "../connections/endpoints"


const EliminarPartidoBoton= ({id})=>{

    const navegar= useNavigate()

    const eliminar = async ()=>{

        await axios.delete(`${ELIMINARPARTIDO_DELETE_ENDPOINT}/${id}`)
        .then(respuesta=>{
            navegar("/")
        })
        .catch(err=>{
            console.error(err)
        })
    }

    const crearAlerta = ()=>{
        
        const titulo= "Eliminar partido \n ¿Desea eliminar el partido?"

        return (window.confirm(titulo) === true) ? eliminar() : ()=>{}
    }

    return(
        <Button variant='danger' size="sm" onClick={crearAlerta}>
            Eliminar
        </Button>
    )
}

export {EliminarPartidoBoton}