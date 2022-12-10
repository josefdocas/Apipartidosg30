import axios from "axios"
import { useState } from "react"
import { Alert, Col, Container, Row } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import CrearPartidoFormulario from "../components/CrearPartidoFormulario"
import { CREARPARTIDO_POST_ENDPOINT } from "../connections/endpoints"

const CrearPartido= ()=>{

    const navegar= useNavigate()
    const [errores, setErrores]= useState({})

    const crear=  async({fecha, equipoLocal, equipoVisitante})=>{

        const error= {}
        setErrores(error)

        await axios.post(CREARPARTIDO_POST_ENDPOINT, {fecha, equipoLocal, equipoVisitante},
            {Headers: {"Accept": "application/json", "Content-Type": "application/json"}}    
        )
        .then(respuesta=>{
            navegar(`/partido/${respuesta.data.idPartido}`)
        })
        .catch(err=>{
            console.error(err)
            setErrores({nuevo: err.respuesta.data.message})

        })
    }
    
    return(
        <Container className="mt-3 mb-3">
            <Row className="justify-content-md-center">
                <Col sm="12" md="8" lg="6">
                    <h3 className="text-center">Crear Partido</h3>
                    <div>
                        {errores.nuevo && <Alert variant="danger"> {errores.nuevo} </Alert>}
                        <CrearPartidoFormulario callback={crear} errores={errores} editar={false}/>
                    </div>
                </Col>
            </Row>
        </Container>
    )
}

export {CrearPartido}