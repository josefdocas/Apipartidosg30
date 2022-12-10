import axios from "axios"
import { useEffect, useState } from "react"
import { Col, Container, Row } from "react-bootstrap"
import { PartidoCard } from "../components/PartidoCard"
import { MISPARTIDOS_GET_ENDPOINT } from "../connections/endpoints"

const Mispartidos=()=>{

    const [partidos, setPartidos]= useState([])
    const [buscando, setBuscando]= useState(true)

    useEffect(()=>{
        axios.get(MISPARTIDOS_GET_ENDPOINT)
        .then(respuesta=>{
            setPartidos(respuesta.data)
            setBuscando(false)
        })
        .catch(err=>{
            setBuscando(false)
        })
    }, [])
    


    return(
        <Container className='mt-3 mb-3'>
            <Row className="justify-content-md-center">
                <Col sm="12" md="8" lg="6">
                    <h3 className="text-center">Mis Partidos</h3>
                    <div>
                        {buscando ? "Cargando..." : (partidos.length === 0 && "No hay partidos disponibles")}
                        {partidos.map(partido=> <PartidoCard key={partido.idPartido} partido={partido} botones={true}/>)}
                    </div>
                </Col>
            </Row>
        </Container>
    )
}

export {Mispartidos}