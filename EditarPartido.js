import axios from "axios"
import { useEffect, useState } from "react"
import { Alert, Col, Container, Row } from "react-bootstrap"
import { useNavigate, useParams } from "react-router-dom"
import CrearPartidoFormulario from "../components/CrearPartidoFormulario"
import { EDITARPARTIDO_PUT_ENDPOINT, PARTIDODETALLE_GET_ENDPOINT } from "../connections/endpoints"

const EditarPartido=()=>{

    const [partido, setPartido]= useState(null)
    const [errores, setErrores]= useState([])
    const navegar= useNavigate()
    const {id}= useParams()

    useEffect(()=>{
        axios.get(`${PARTIDODETALLE_GET_ENDPOINT}/${id}`)
        .then(respuesta=>{
            setPartido(respuesta.data)
        })
        .catch(err=>{
            navegar("/")
        })
    }, [id, navegar])

    const editar= ({golesLocal, golesVisitante})=>{

        axios.put(`${EDITARPARTIDO_PUT_ENDPOINT}/${id}`, {golesLocal, golesVisitante})
        .then(respuesta=>{
            navegar("/")
        })
        .catch(err=>{
            setErrores({actualizar: err.respuesta.data.message})
        })
    }
    


    return(
        <Container className="mt-3 mb-3">
            <Row className="justify-content-md-center">
                <Col sm="12" md="8" lg="6">
                    <h3 className="text-center">Editar Partido</h3>
                    <div>
                        {errores.actualizar && <Alert variant="danger">{errores.actualizar}</Alert>}
                        {partido && 
                            <CrearPartidoFormulario 
                                callback={editar} 
                                errores={errores} 
                                editar={true}
                                pfecha={partido.fecha}
                                pnombreLocal={partido.equipoEntityLocal.nombre}
                                pnombreVisitante={partido.equipoEntityVisitante.nombre}
                                pidLocal={partido.equipoEntityLocal.id}
                                pidVisitante={partido.equipoEntityVisitante.id}
                                pgolesLocal={partido.golesLocal}
                                pgolesVisitante={partido.golesVisitante}
                            />

                        }
                    </div>
                </Col>
            </Row>
        </Container>
    )
}

export {EditarPartido}