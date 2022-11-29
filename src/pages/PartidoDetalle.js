import axios from "axios"
import { useEffect, useState } from "react"
import { Badge, Card, Col, Container, Row } from "react-bootstrap"
import { useNavigate, useParams } from "react-router-dom"
import { PartidoCard } from "../components/PartidoCard"
import { PARTIDODETALLE_GET_ENDPOINT } from "../connections/endpoints"

const PartidoDetalle=()=>{

    const [partido, setPartido]= useState(null)
    const {id}= useParams()
    const navegar= useNavigate()


    useEffect(()=>{
        axios.get(`${PARTIDODETALLE_GET_ENDPOINT}/${id}`)
        .then(respuesta=>{
            setPartido(respuesta.data)
        })
        .catch(err=>{
            console.error(err)
            navegar(-1)
        })
    }, [id, navegar])
    


    return(
        <Container className="mt-3 mb-3">
            <Row className="justify-content-md-center">
                <Col sm="12" md="8" lg="6">
                    <h3 className="text-center">Detalle del Partido</h3>
                    {partido && (
                        <Card className='mt-5 mb-5'>
                         <Card.Header className="mi-card">
                                {partido.equipoEntityLocal.nombre} vs {partido.equipoEntityVisitante.nombre}
                                {partido.jugado ? <Badge className='mi-badge-jugado'>Jugado</Badge> : <Badge className='mi-badge-pendiente'>Pendiente</Badge>}
                        </Card.Header>
                        <Card.Body>
                            <p>
                                Local 
                                <Badge className='mi-badge-marcador'>{partido.golesLocal}</Badge>
                                vs
                                <Badge className='mi-badge-marcador'>{partido.golesVisitante}</Badge>
                                Visitante
                            </p>
                                Fecha: {partido.fecha} 
                        </Card.Body>
                        <Card.Footer>
                        Creado por: {partido.usuarioEntity.nombre}, el dia {partido.creado} 
                        </Card.Footer>
                    </Card> 
                    )} 
                </Col>
            </Row>
        </Container>
                    )
}

export {PartidoDetalle}